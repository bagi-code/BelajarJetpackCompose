package com.bagicode.myjetpackcompose.repository

import com.bagicode.myjetpackcompose.model.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val loginApi = retrofit.create(LoginApi::class.java)

    suspend fun login(username: String, password: String): LoginResponse {
        return loginApi.login(username, password)
    }
}
