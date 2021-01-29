package com.mionix.topsound.model

import com.google.gson.annotations.SerializedName

data class UserInfo (
        @SerializedName("user")
        val user : User,
        @SerializedName("token")
        val token : String
)