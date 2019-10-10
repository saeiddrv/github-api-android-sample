package ir.sdrv.mobilletsample.app.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ir.sdrv.mobilletsample.BuildConfig
import ir.sdrv.mobilletsample.data.remote.api.GithubApi
import ir.sdrv.mobilletsample.domain.api.GithubApiClient
import ir.sdrv.mobilletsample.domain.api.GithubApiClientImpl
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

const val TOME_OUT: Long = 30

val githubApiModule = module {
    factory { provideHttpLoggingInterceptor() }
    factory { provideGson() }
    factory { provideOkHttpClient(get()) }
    factory { provideGithubApi(get()) }
    single { provideRetrofit(get(), get()) }
}

val githubApiClientModule = module {
    single<GithubApiClient> { GithubApiClientImpl(get()) }
}

fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setLenient()
        .create()
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

    val builder = OkHttpClient.Builder()

    builder
        .connectTimeout(TOME_OUT, TimeUnit.SECONDS)
        .readTimeout(TOME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TOME_OUT, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader(
                "Authorization",
                Credentials.basic("saeiddrv", "b29272629e9a6f98eac243c8cc6f4bc787a52ff2")
            )
            chain.proceed(requestBuilder.build())
        }

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(httpLoggingInterceptor)
    }

    return builder.build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.tag("GithubAPI").i(message)
        }
    })
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}