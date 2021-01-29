package com.mionix.topsound.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mionix.topsound.R
import com.mionix.topsound.local.pref.AppPreferences
import com.mionix.topsound.ui.MainActivity
import com.mionix.topsound.ui.login.LoginActivity
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    private val mSharePreferences by inject<AppPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkUserInfo()
    }

    private fun checkUserInfo() {

        Timer("Loading Splash Screen",false).schedule(timerTask {
            mSharePreferences.getTokenUserInfo().let {
                if (it == null) {
                    sendUserToLogin()
                } else {
                    sendUserToMain()
                }
            }

        }, 2000)

    }

    private fun sendUserToMain() {
        val intent = Intent(this@SplashActivity,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun sendUserToLogin() {
        val intent = Intent(this@SplashActivity,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}