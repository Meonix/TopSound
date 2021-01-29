package com.mionix.topsound.model

import com.google.gson.annotations.SerializedName


data class Tokens (
        @SerializedName("_id")
        val _id : String,
        @SerializedName("token")
        val token : String
)