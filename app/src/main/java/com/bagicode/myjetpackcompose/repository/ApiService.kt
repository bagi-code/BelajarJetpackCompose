package com.bagicode.myjetpackcompose.repository

import com.bagicode.myjetpackcompose.model.LoginResponse
import com.bagicode.myjetpackcompose.model.ProductResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("products")
    suspend fun products(): ProductResponse
}
