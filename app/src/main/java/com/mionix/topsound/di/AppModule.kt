package com.mionix.topsound.di

import com.mionix.topsound.App
import com.mionix.topsound.local.pref.AppPreferences
import com.securepreferences.SecurePreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { provideSecurePreferences(androidApplication() as App) }
    single { provideAppSharePreferences(get()) }

}
fun provideSecurePreferences(app: App): SecurePreferences {
    return SecurePreferences(app, "", "topsound.xml")
}
fun provideAppSharePreferences(sharedPreferences: SecurePreferences) : AppPreferences {
    return AppPreferences(sharedPreferences)
}