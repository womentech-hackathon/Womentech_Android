package com.ssjm.sw_hackathon.accountApi.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("user/login")
    fun login(
        @Body params: LoginRequest
    ): Call<LoginResponse>
}