package com.mionix.topsound.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mionix.topsound.base.BaseViewModel
import com.mionix.topsound.base.onFailure
import com.mionix.topsound.base.onLoading
import com.mionix.topsound.base.onSuccess
import com.mionix.topsound.model.UserInfo
import com.mionix.topsound.repo.RegisterRepo
import kotlinx.coroutines.launch

class RegisterViewModel (private val registerRepo: RegisterRepo): BaseViewModel() {
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: MutableLiveData<Boolean>
        get() = _registerResult

    private val _registerErrorCode = MutableLiveData<Int>()
    val registerErrorCode: MutableLiveData<Int>
        get() = _registerErrorCode

    private val _getUserInfo = MutableLiveData<UserInfo>()
    val getUserInfo: LiveData<UserInfo> get() = _getUserInfo

    fun register(name :String, email:String , password:String) {
        _isLoading.value = true
        viewModelScope.launch {
            registerRepo.register(name,email, password)
                .onLoading {
                    println("Loading $it")
                }
                .onSuccess {
                    _isLoading.value = false
                    _registerResult.value = true
                    _getUserInfo.postValue(it)

                }
                .onFailure {
                    _registerResult.value = false
                    _registerErrorCode.value = it.code
                    _isLoading.value = false
                }
        }
    }
}