package com.ssjm.sw_hackathon.onBoardingApi

import android.content.ContentValues
import android.util.Log
import com.ssjm.sw_hackathon.apiClient.ApiClient
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalRequest
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalResponse
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalService
import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// api 통신을 위한 retrofit
private val retrofit: Retrofit = ApiClient.getInstance()

private var accessTokenValue: String? = null
private var refreshTokenValue: String? = null

fun setToken() {
    accessTokenValue = "Bearer " + GloabalApplication.prefs.getString("accessToken", "")
    refreshTokenValue = GloabalApplication.prefs.getString("refreshToken", "")
}

// 목표 생성
fun apiAddGoal(
    addGoalRequest: AddGoalRequest,
    doneOnBoarding: () -> Unit
) {
    setToken()
    retrofit.create(AddGoalService::class.java)
        .addGoal(authorization = accessTokenValue!!, refreshToken = refreshTokenValue!!, params = addGoalRequest)
        .enqueue(object : Callback<AddGoalResponse> {
            override fun onResponse(call: Call<AddGoalResponse>, response: Response<AddGoalResponse>) {
                Log.d(ContentValues.TAG, "목표 생성 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    doneOnBoarding()
                }
            }

            override fun onFailure(call: Call<AddGoalResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "목표 생성 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}