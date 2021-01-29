package com.mionix.topsound.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mionix.topsound.base.BaseViewModel
import com.mionix.topsound.base.onFailure
import com.mionix.topsound.base.onLoading
import com.mionix.topsound.base.onSuccess
import com.mionix.topsound.model.UserInfo
import com.mionix.topsound.repo.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepo: LoginRepo): BaseViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: MutableLiveData<Boolean>
        get() = _loginResult

    private val _loginErrorMsg = MutableLiveData<String>()
    val loginErrorMsg: MutableLiveData<String>
        get() = _loginErrorMsg

    private val _getUserInfo = MutableLiveData<UserInfo>()
    val getUserInfo: LiveData<UserInfo> get() = _getUserInfo

    fun login(email:String , password:String) {
        _isLoading.value = true
        viewModelScope.launch {
            loginRepo.login(email, password)
                    .onLoading {
                        println("Loading $it")
                    }
                    .onSuccess {
                        _isLoading.value = false
                        _loginResult.value = true
                        _getUserInfo.postValue(it)

                    }
                    .onFailure {
                        _loginResult.value = false
                        _loginErrorMsg.value = it.msg
                        _isLoading.value = false
                    }
        }
    }
}