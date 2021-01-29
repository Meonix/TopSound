package com.mionix.topsound.di

import com.mionix.topsound.App
import com.mionix.topsound.local.pref.AppPreferences
import com.mionix.topsound.repo.LoginRepo
import com.mionix.topsound.repo.RegisterRepo
import com.securepreferences.SecurePreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepo(get()) }
    single { RegisterRepo(get()) }
//    single { SourcesNewsRepo(get()) }
}
