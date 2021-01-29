package com.mionix.topsound.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mionix.topsound.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.launch

open class BaseViewModel :ViewModel() {
    protected val _isLoading = SingleLiveEvent<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _errorExpired = SingleLiveEvent<String>()
    val errorExpired: MutableLiveData<String>
        get() = _errorExpired

    private val _errorAPI = SingleLiveEvent<String>()
    val errorAPI: MutableLiveData<String>
        get() = _errorAPI

//    fun handleExpired(expired: ResponseError?) {
//        expired?.let {
//            if (it.status_code == AppConstants.STATUS_EXPIRED) {
//                errorExpired.postValue(it.status_message)
//            }
//        }
//    }

//    fun handleErrorApi(error: ResponseError?){
//        error?.let {
//            errorAPI.postValue(it.status_message)
//        }
//    }
}