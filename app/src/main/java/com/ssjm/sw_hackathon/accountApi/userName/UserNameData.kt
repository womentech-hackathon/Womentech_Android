package com.ssjm.sw_hackathon.accountApi.userName

import com.google.gson.annotations.SerializedName

data class UserNameResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: UserName,
)

data class UserName(
    @SerializedName("name")
    val name: String,
)
