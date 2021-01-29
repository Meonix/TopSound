package com.mionix.topsound.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mionix.topsound.R
import com.mionix.topsound.databinding.ActivityLoginBinding
import com.mionix.topsound.databinding.ActivityRegisterBinding
import com.mionix.topsound.local.pref.AppPreferences
import com.mionix.topsound.ui.MainActivity
import com.mionix.topsound.ui.login.LoginViewModel
import com.mionix.topsound.utils.CheckValidData
import com.mionix.topsound.utils.CheckValidData.isEmailValid
import com.mionix.topsound.utils.Keyboard
import com.mionix.topsound.utils.views.LoadingDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loadingDialog: LoadingDialog
    private val mRegisterViewModel: RegisterViewModel by viewModel()
    private val mSharePreferences by inject<AppPreferences>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        setupView()
        setupViewModel()
        handleOnClick()
    }

    private fun setupViewModel() {
        mRegisterViewModel.getUserInfo.observe(this@RegisterActivity, {
            if (it.token.isNotEmpty()) {
                mSharePreferences.setTokenUserInfo(it.token)
                sendUserToMain()
            }
        })

        mRegisterViewModel.registerErrorCode.observe(this@RegisterActivity, {
            if (it != null) {
                loadingDialog.dismissDialog()
                if (it == 409) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Your account already exists",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "something went wrong.($it)",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        })
        mRegisterViewModel.isLoading.observe(this@RegisterActivity, {
            if (it) {
                loadingDialog.startLoadingDialog()
            } else {
                loadingDialog.dismissDialog()
            }
        })
    }

    private fun handleOnClick() {
        binding.vBack.setOnClickListener {
            onBackPressed()
            finish()
        }
        binding.btRegister.setOnClickListener {
            register()
        }
    }

    private fun sendUserToMain() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun register() {
        val email = binding.etEmail.editText?.text.toString()
        val password = binding.etPassword.editText?.text.toString()
        val name = binding.etName.editText?.text.toString()
        val confirmPassword = binding.etConfirmPassword.editText?.text.toString()
        if (name.isNullOrEmpty()) {
            binding.etName.error = getString(R.string.error_name_register)
        } else if (email.isNullOrEmpty()) {
            binding.etEmail.error = getString(R.string.error_email_register)
        } else if (password.isNullOrEmpty()) {
            binding.etPassword.error = getString(R.string.error_password_register)
        } else if (password.length < 7) {
            binding.etPassword.error = getString(R.string.password_must_be_at_least_7_char)
        } else if (confirmPassword.isNullOrEmpty()) {
            binding.etConfirmPassword.error = getString(R.string.confirm_password_register)
        }
        if (password != confirmPassword) {
            Toast.makeText(
                this@RegisterActivity,
                "password and confirm password do not match.",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (!isEmailValid(email)) {
            Toast.makeText(this@RegisterActivity, "email is not valid.", Toast.LENGTH_SHORT).show()
        } else if (isEmailValid(email) && password == confirmPassword && !name.isNullOrEmpty() && password.length >= 7) {
            Log.d("DUY", "" + name + password + confirmPassword + email)
            mRegisterViewModel.register(name, email, password)
        }
    }

    private fun setupView() {
        //hide keyboard when click out side
        Keyboard(this@RegisterActivity).setUpActionKeyBoard(binding.root)
        loadingDialog = LoadingDialog(this@RegisterActivity)
    }
}