package com.ssjm.sw_hackathon.accountApi.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username: String, // 아이디

    @SerializedName("password")
    val password: String, // 비밀번호
)

data class LoginResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: LoginResult,
)

data class LoginResult(
    @SerializedName("accessToken")
    val accessToken: String?,

    @SerializedName("refreshToken")
    val refreshToken: String?,
)