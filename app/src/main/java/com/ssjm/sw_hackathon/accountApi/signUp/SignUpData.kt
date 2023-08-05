package com.ssjm.sw_hackathon.accountApi.signUp

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username")
    val username: String, // 아이디

    @SerializedName("name")
    val name: String, // 이름

    @SerializedName("password")
    val password: String, // 비밀번호
)

data class SignUpResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: String?,
)