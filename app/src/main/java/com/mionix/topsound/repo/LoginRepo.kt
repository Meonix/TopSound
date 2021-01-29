package com.mionix.topsound.repo

import android.util.Log
import com.mionix.topsound.api.ApiWebService
import com.mionix.topsound.base.Response
import com.mionix.topsound.base.ResponseError
import com.mionix.topsound.model.UserInfo
import com.mionix.topsound.utils.ApiErrorHandler
import okhttp3.ResponseBody
import retrofit2.HttpException

class LoginRepo(private val apiWebService: ApiWebService) {
    suspend fun login(email:String,password:String) : Response<UserInfo> {
        return try {
            val response = apiWebService.login(email,password)
            Log.d("DUY", response.token)
            Response.success(response)
        }catch (e: Exception){
            e.printStackTrace()
//            Response.error(ApiErrorHandler.handleErrorResponse(e))
            Response.error(ResponseError((e as HttpException).code(),e.message.toString()))
        }
    }

}