package com.ssjm.sw_hackathon.educationApi

import android.content.ContentValues
import android.util.Log
import com.ssjm.sw_hackathon.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// api 통신을 위한 retrofit
private val retrofit: Retrofit = EducationApiClient.getInstance()

// SEOUL OPEN API KEY
private const val API_KEY = BuildConfig.SEOUL_OPEN_API_KEY

// 서울시 어르신 취업지원센터 교육정보 조회
fun apiGetEducationInfo(
    startIndex: Int,
    endIndex: Int,
    addEducationList: (item: MutableList<EducationRow>) -> Unit
) {
    retrofit.create(EducationService::class.java)
        .getEducation(API_KEY, startIndex, endIndex)
        .enqueue(object : Callback<EducationData> {
            override fun onResponse(call: Call<EducationData>, response: Response<EducationData>) {
                Log.d(ContentValues.TAG, "서울시 어르신 취업지원센터 교육정보 조회 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                val body: EducationData? = response.body()!!
                if(body != null) {
                    val educationList: MutableList<EducationRow> = body.tbViewProgram.row
                    addEducationList(educationList)
                }
            }

            override fun onFailure(call: Call<EducationData>, t: Throwable) {
                Log.d(ContentValues.TAG, "서울시 어르신 취업지원센터 교육정보 조회 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}