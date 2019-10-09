package ir.sdrv.mobilletsample.app

import android.app.Application
import ir.sdrv.mobilletsample.BuildConfig
import ir.sdrv.mobilletsample.app.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(
                usersListViewModel,
                githubApiModule,
                githubApiClientModule
            ))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}