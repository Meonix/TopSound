package com.mionix.topsound.base


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.RuntimeException

data class ResponseError(
        val code: Int,
        val msg: String
) : RuntimeException(), Serializable