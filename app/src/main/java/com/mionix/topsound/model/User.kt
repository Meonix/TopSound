package com.mionix.topsound.model

import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("_id")
        val _id : String,
        @SerializedName("name")
        val name : String,
        @SerializedName("email")
        val email : String,
        @SerializedName("password")
        val password : String,
        @SerializedName("tokens")
        val tokens : List<Tokens>,
        @SerializedName("createdAt")
        val createdAt : String,
        @SerializedName("updatedAt")
        val updatedAt : String,
        @SerializedName("__v")
        val __v : Int
)