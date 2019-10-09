package ir.sdrv.mobilletsample.app.modules

import ir.sdrv.mobilletsample.presentation.datasource.UsersListDataSourceFactory
import org.koin.dsl.module

val usersListDataSourceFactory = module {
    single { UsersListDataSourceFactory(get()) }
}