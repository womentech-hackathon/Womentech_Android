package com.ssjm.sw_hackathon.accountApi.signUp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("user/join")
    fun signUp(
        @Body params: SignUpRequest
    ): Call<SignUpResponse>
}