package com.ssjm.sw_hackathon.accountApi

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.ssjm.sw_hackathon.accountApi.login.LoginRequest
import com.ssjm.sw_hackathon.accountApi.login.LoginResponse
import com.ssjm.sw_hackathon.accountApi.login.LoginResult
import com.ssjm.sw_hackathon.accountApi.login.LoginService
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpRequest
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpResponse
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpService
import com.ssjm.sw_hackathon.accountApi.userName.UserNameResponse
import com.ssjm.sw_hackathon.accountApi.userName.UserNameService
import com.ssjm.sw_hackathon.apiClient.ApiClient
import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// api 통신을 위한 retrofit
private val retrofit: Retrofit = ApiClient.getInstance()

private var accessTokenValue: String? = null
private var refreshTokenValue: String? = null

fun setToken() {
    accessTokenValue = "Bearer " + GloabalApplication.prefs.getString("accessToken", "")
    refreshTokenValue = GloabalApplication.prefs.getString("refreshToken", "")
}

// 회원가입
fun apiSignUp(
    signUpRequest: SignUpRequest,
    checkComplete: (isComplete: Boolean) -> Unit
) {
    retrofit.create(SignUpService::class.java)
        .signUp(signUpRequest)
        .enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                Log.d(TAG, "회원가입 결과 -------------------------------------------")
                Log.d(TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    if(response.body()!!.success) {
                        checkComplete(true)
                    }
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d(TAG, "회원가입 결과 fail -------------------------------------------")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
}

// 로그인
fun apiLogin(
    loginRequest: LoginRequest,
    checkComplete: (token: LoginResult) -> Unit
) {
    retrofit.create(LoginService::class.java)
        .login(loginRequest)
        .enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d(TAG, "로그인 결과 -------------------------------------------")
                Log.d(TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    if (response.body()!!.data != null) {
                        checkComplete(response.body()!!.data)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d(TAG, "로그인 결과 fail -------------------------------------------")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
}

// 이름 조회
fun apiGetUserName(
    setUserName: (name: String?) -> Unit
) {
    setToken()
    retrofit.create(UserNameService::class.java)
        .getUserName(accessTokenValue!!, refreshTokenValue!!)
        .enqueue(object : Callback<UserNameResponse> {
            override fun onResponse(call: Call<UserNameResponse>, response: Response<UserNameResponse>) {
                Log.d(TAG, "사용자명 조회 결과 -------------------------------------------")
                Log.d(TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    val userName = response.body()!!.data.name
                    setUserName(userName)
                }

                else {
                    setUserName(null)
                }
            }

            override fun onFailure(call: Call<UserNameResponse>, t: Throwable) {
                Log.d(TAG, "사용자명 조회 결과 fail -------------------------------------------")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
}