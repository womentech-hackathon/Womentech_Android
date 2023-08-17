package com.ssjm.sw_hackathon.goalApi

import android.content.ContentValues
import android.util.Log
import com.ssjm.sw_hackathon.apiClient.ApiClient
import com.ssjm.sw_hackathon.goalApi.getDailyTasks.GetDailyTask
import com.ssjm.sw_hackathon.goalApi.getDailyTasks.GetDailyTasksResponse
import com.ssjm.sw_hackathon.goalApi.getDailyTasks.GetDailyTasksService
import com.ssjm.sw_hackathon.goalApi.getProgressGoal.GetProgressGoalResponse
import com.ssjm.sw_hackathon.goalApi.getProgressGoal.GetProgressGoalResult
import com.ssjm.sw_hackathon.goalApi.getProgressGoal.GetProgressGoalService
import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDate

// api 통신을 위한 retrofit
private val retrofit: Retrofit = ApiClient.getInstance()

private var accessTokenValue: String? = null
private var refreshTokenValue: String? = null
fun setToken() {
    accessTokenValue = "Bearer " + GloabalApplication.prefs.getString("accessToken", "")
    refreshTokenValue = GloabalApplication.prefs.getString("refreshToken", "")
}

// 목표 조회
fun apiGetProgressGoal(
    setGoalInfo: (goalInfo: GetProgressGoalResult) -> Unit
) {
    setToken()
    retrofit.create(GetProgressGoalService::class.java)
        .getProgressGoal(authorization = accessTokenValue!!, refreshToken = refreshTokenValue!!)
        .enqueue(object : Callback<GetProgressGoalResponse> {
            override fun onResponse(call: Call<GetProgressGoalResponse>, response: Response<GetProgressGoalResponse>) {
                Log.d(ContentValues.TAG, "진행 중 목표 조회 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    val goalInfo = response.body()!!.data
                    setGoalInfo(goalInfo)
                }
            }

            override fun onFailure(call: Call<GetProgressGoalResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "진행 중 목표 조회 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}

// 오늘의 할 일 조회
fun apiGetDailyTasks(
    goalId: Int,
    date: LocalDate,
    setDailyTask: (dailyTasks: MutableList<GetDailyTask>) -> Unit
) {
    setToken()
    retrofit.create(GetDailyTasksService::class.java)
        .getDailyTasksGoal(authorization = accessTokenValue!!, refreshToken = refreshTokenValue!!, goalId = goalId, date = date)
        .enqueue(object : Callback<GetDailyTasksResponse> {
            override fun onResponse(call: Call<GetDailyTasksResponse>, response: Response<GetDailyTasksResponse>) {
                Log.d(ContentValues.TAG, "daily 실천 사항 조회 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    setDailyTask(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<GetDailyTasksResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "daily 실천 사항 조회 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}