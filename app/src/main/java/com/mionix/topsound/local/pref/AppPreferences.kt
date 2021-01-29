package com.mionix.topsound.local.pref

import com.securepreferences.SecurePreferences

class AppPreferences(private val securePreferences: SecurePreferences) {
    companion object {
        const val TOKEN_USER_INFO = "token_user_info"

    }
    fun setTokenUserInfo(token: String?) {
        securePreferences.edit().putString(TOKEN_USER_INFO, token).apply()
    }

    fun getTokenUserInfo() = securePreferences.getString(TOKEN_USER_INFO, null)


}