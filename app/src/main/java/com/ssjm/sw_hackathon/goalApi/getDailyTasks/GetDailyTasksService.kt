package com.ssjm.sw_hackathon.goalApi.getDailyTasks

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface GetDailyTasksService {
    @GET("goals/{goal_id}/daily-tasks")
    fun getDailyTasksGoal(
        @Header("Authorization") authorization: String, // 로그인으로 발급받은 AccessToken: JWT {발급받은 토큰} 형태로 입력
        @Header("refresh-token") refreshToken: String,  // 로그인으로 발급받은 RefreshToken
        @Path("goal_id") goalId: Int,
        @Query("date") date: LocalDate
    ): Call<GetDailyTasksResponse>
}