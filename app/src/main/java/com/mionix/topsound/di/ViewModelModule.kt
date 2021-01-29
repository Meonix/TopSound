package com.mionix.topsound.di

import com.mionix.topsound.repo.RegisterRepo
import com.mionix.topsound.ui.login.LoginViewModel
import com.mionix.topsound.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterViewModel(get()) }
//    single { ActivityViewModel() }
//    viewModel { SearchNewsViewModel(get()) }
//    viewModel { SourcesNewsViewModel(get()) }
//    viewModel { SpinnerViewModel() }
    viewModel { LoginViewModel(get()) }
//    viewModel { ProfileViewModel(get()) }
//    viewModel { NewsDetailViewModel(get()) }
}