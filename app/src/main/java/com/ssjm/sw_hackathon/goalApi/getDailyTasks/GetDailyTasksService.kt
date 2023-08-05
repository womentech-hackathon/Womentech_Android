package com.ssjm.sw_hackathon.goalApi.getDailyTasks

import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

private var accessTokenValue: String = "Bearer " + GloabalApplication.prefs.getString("accessToken", "")
private var refreshTokenValue: String = GloabalApplication.prefs.getString("refreshToken", "")
private var goalIdValue: Int = GloabalApplication.prefs.getInt("goalId", 0)

interface GetDailyTasksService {
    @GET("goals/{goal_id}/daily-tasks")
    fun getDailyTasksGoal(
        @Header("Authorization") authorization: String = accessTokenValue, // 로그인으로 발급받은 AccessToken: JWT {발급받은 토큰} 형태로 입력
        @Header("refresh-token") refreshToken: String = refreshTokenValue,  // 로그인으로 발급받은 RefreshToken
        @Path("goal_id") goalId: Int = goalIdValue,
        @Query("date") date: LocalDate
    ): Call<GetDailyTasksResponse>
}