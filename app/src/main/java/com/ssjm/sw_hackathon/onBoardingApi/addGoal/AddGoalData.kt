package com.ssjm.sw_hackathon.onBoardingApi.addGoal

import com.google.gson.annotations.SerializedName

data class AddGoalRequest(
    @SerializedName("tasks")
    val tasks: MutableList<AddGoalTasks>, // 실천사항

    @SerializedName("name")
    val name: String,        // 목표 직업
)

data class AddGoalTasks(
    @SerializedName("name")
    val name: String,       // 실천사항 내용

    @SerializedName("days")
    val days: MutableList<String>, // 요일
)

data class AddGoalResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: String?,
)