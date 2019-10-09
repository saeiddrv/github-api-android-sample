package ir.sdrv.mobilletsample.app.modules

import ir.sdrv.mobilletsample.presentation.viewmodel.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usersListViewModel = module {
    viewModel { UsersListViewModel() }
}
