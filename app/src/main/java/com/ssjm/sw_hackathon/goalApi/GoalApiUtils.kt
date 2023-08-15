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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDate

// api 통신을 위한 retrofit
private val retrofit: Retrofit = ApiClient.getInstance()

// 목표 조회
fun apiGetProgressGoal(
    setGoalInfo: (goalInfo: GetProgressGoalResult) -> Unit
) {
    retrofit.create(GetProgressGoalService::class.java)
        .getProgressGoal()
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
    retrofit.create(GetDailyTasksService::class.java)
        .getDailyTasksGoal(goalId = goalId, date = date)
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