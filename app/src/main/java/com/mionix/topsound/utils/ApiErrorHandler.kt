package com.mionix.topsound.utils

import com.mionix.topsound.base.ResponseError
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ApiErrorHandler {
    private const val API_KEY_INVALID = 7

    fun handleErrorResponse(e: Throwable): ResponseError {
        try {
            return when (e) {
//                is HttpException -> {
//                    val responseBody = e.response()?.errorBody()
//                    val jsonObject = JSONObject(responseBody!!.string())
//
//                    val messageServerMsg = jsonObject.getString("message")
//                    val paramErrorServerMsg = jsonObject.getString("param_errors")
//                    val code = 400
//
//                    ResponseError(code, getMessage(code, messageServerMsg),param_errors = paramErrorServerMsg)
//                }
                is UnknownHostException -> ResponseError(400, "No internet connection!")
//                is SocketTimeoutException -> ResponseError(400, "Can't connect to server!")
                else -> ResponseError(401, e.message.toString())
            }
        } catch (ex: Exception) {
            return ResponseError(500, "Internal Server Error")
        }

    }

    private fun getMessage(code: Int, serverMsg: String): String {
        val defaulMsg = "Somethings wrong!"
        when (code) {
            API_KEY_INVALID -> return defaulMsg
        }
        return serverMsg
    }
}
