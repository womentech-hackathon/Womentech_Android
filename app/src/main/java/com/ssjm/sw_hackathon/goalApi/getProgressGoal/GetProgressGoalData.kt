package com.ssjm.sw_hackathon.goalApi.getProgressGoal

import com.google.gson.annotations.SerializedName

data class GetProgressGoalResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: GetProgressGoalResult,
)

data class GetProgressGoalResult(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("tasks")
    val tasks: MutableList<GetProgressGoalTask>,
)

data class GetProgressGoalTask(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("days")
    val days: MutableList<String>,

    @SerializedName("status")
    val status: String,
)
