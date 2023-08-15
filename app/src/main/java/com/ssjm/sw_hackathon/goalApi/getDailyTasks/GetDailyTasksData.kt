package com.ssjm.sw_hackathon.goalApi.getDailyTasks

import com.google.gson.annotations.SerializedName

data class GetDailyTasksResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MutableList<GetDailyTask>,
)

data class GetDailyTask(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("days")
    val days: MutableList<String>,

    @SerializedName("status")
    val status: String,
)