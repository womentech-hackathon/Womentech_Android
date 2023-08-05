package com.ssjm.sw_hackathon.onBoardingApi

import android.content.ContentValues
import android.util.Log
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpRequest
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpResponse
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpService
import com.ssjm.sw_hackathon.apiClient.ApiClient
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalRequest
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalResponse
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalService
import com.ssjm.sw_hackathon.onBoardingApi.getProgressGoal.GetProgressGoalResponse
import com.ssjm.sw_hackathon.onBoardingApi.getProgressGoal.GetProgressGoalService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// api 통신을 위한 retrofit
private val retrofit: Retrofit = ApiClient.getInstance()

// 목표 생성
fun apiAddGoal(
    addGoalRequest: AddGoalRequest,
    getGoalId: (goalId: Int) -> Unit
) {
    retrofit.create(AddGoalService::class.java)
        .addGoal(params = addGoalRequest)
        .enqueue(object : Callback<AddGoalResponse> {
            override fun onResponse(call: Call<AddGoalResponse>, response: Response<AddGoalResponse>) {
                Log.d(ContentValues.TAG, "목표 생성 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    apiGetProgressGoal(
                        getGoalId = {
                            getGoalId(it)
                        }
                    )
                }
            }

            override fun onFailure(call: Call<AddGoalResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "목표 생성 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}

fun apiGetProgressGoal(
    getGoalId: (goalId: Int) -> Unit
) {
    retrofit.create(GetProgressGoalService::class.java)
        .getProgressGoal()
        .enqueue(object : Callback<GetProgressGoalResponse> {
            override fun onResponse(call: Call<GetProgressGoalResponse>, response: Response<GetProgressGoalResponse>) {
                Log.d(ContentValues.TAG, "진행 중 목표 조회 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    val id = response.body()!!.data.id
                    getGoalId(id)
                }
            }

            override fun onFailure(call: Call<GetProgressGoalResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "진행 중 목표 조회 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}