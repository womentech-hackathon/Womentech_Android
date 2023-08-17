package com.ssjm.sw_hackathon.onBoardingApi.addGoal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface AddGoalService {
    @POST("goals")
    fun addGoal(
        @Header("Authorization") authorization: String, // 로그인으로 발급받은 AccessToken: JWT {발급받은 토큰} 형태로 입력
        @Header("refresh-token") refreshToken: String,  // 로그인으로 발급받은 RefreshToken
        @Body params: AddGoalRequest
    ): Call<AddGoalResponse>
}