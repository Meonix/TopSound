package com.mionix.topsound.repo

import android.util.Log
import com.mionix.topsound.api.ApiWebService
import com.mionix.topsound.base.Response
import com.mionix.topsound.base.ResponseError
import com.mionix.topsound.model.UserInfo
import retrofit2.HttpException

class RegisterRepo (private val apiWebService: ApiWebService) {
    suspend fun register(name:String,email:String,password:String) : Response<UserInfo> {
        return try {
            val response = apiWebService.register(name,email,password)
            Response.success(response)
        }catch (e: Exception){
            e.printStackTrace()
//            Response.error(ApiErrorHandler.handleErrorResponse(e))
            Response.error(ResponseError((e as HttpException).code(),e.message.toString()))
        }
    }

}