package com.mionix.topsound.api

import com.mionix.topsound.model.UserInfo
import retrofit2.http.*

interface ApiWebService {
    @FormUrlEncoded
    @POST("user_info/login/")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): UserInfo
    @FormUrlEncoded
    @POST("user_info/create_user/")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ):UserInfo
//
//    @GET("everything")
//    suspend fun getSearchNews(
//        @Query("q") keyWord: String,
//        @Query("page") page: Int,
//        @Query("language") language: String
//    ): ListPopularNews
//    @GET("sources")
//    suspend fun getSourcesNews(
//        @Query("country") country:String
//    ): Sources
}