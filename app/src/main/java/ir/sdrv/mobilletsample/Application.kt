package ir.sdrv.mobilletsample

import android.app.Application
import ir.sdrv.mobilletsample.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(listOf(
                usersListViewModel
            ))
        }
    }

}