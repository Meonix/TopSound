package com.mionix.topsound.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mionix.topsound.databinding.ActivityLoginBinding
import com.mionix.topsound.local.pref.AppPreferences
import com.mionix.topsound.ui.MainActivity
import com.mionix.topsound.ui.register.RegisterActivity
import com.mionix.topsound.utils.CheckValidData.isEmailValid
import com.mionix.topsound.utils.Keyboard
import com.mionix.topsound.utils.views.LoadingDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val mLoginViewModel: LoginViewModel by viewModel()
    private val mSharePreferences by inject<AppPreferences>()
    private lateinit var loadingDialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        setupViewModel()
        setupView()
        handleOnClick()
    }

    private fun setupView() {
        //hide keyboard when click out side
        Keyboard(this@LoginActivity).setUpActionKeyBoard(binding.root)
        loadingDialog = LoadingDialog(this@LoginActivity)
    }

    private fun handleOnClick() {
        binding.btLogin.setOnClickListener {
            login()
        }
        binding.btRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isEmpty()
            || password.isEmpty()
        ) {
            Toast.makeText(
                this@LoginActivity,
                "email and password should not empty.",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!isEmailValid(email)) {
            Toast.makeText(this@LoginActivity, "email is not valid.", Toast.LENGTH_SHORT).show()
        } else {
            mLoginViewModel.login(email, password)
        }
    }


    private fun setupViewModel() {
        mLoginViewModel.getUserInfo.observe(this@LoginActivity, {
            if (it.token.isNotEmpty()) {
                mSharePreferences.setTokenUserInfo(it.token)
                sendUserToMain()
            }
        })

        mLoginViewModel.loginErrorMsg.observe(this@LoginActivity, {
            if (!it.isNullOrEmpty()) {
                loadingDialog.dismissDialog()
                Toast.makeText(this@LoginActivity, "something went wrong.($it)", Toast.LENGTH_LONG)
                    .show()
            }
        })
        mLoginViewModel.isLoading.observe(this@LoginActivity, {
            if (it) {
                loadingDialog.startLoadingDialog()
            } else {
                loadingDialog.dismissDialog()
            }
        })
    }

    private fun sendUserToMain() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }
}