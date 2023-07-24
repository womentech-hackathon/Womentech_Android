package com.ssjm.sw_hackathon.educationApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// 서울시 어르신 취업지원센터 교육정보 api
interface EducationService {
    @GET("{KEY}/json/tbViewProgram/{START_INDEX}/{END_INDEX}")
    fun getEducation(
        @Path("KEY") KEY: String,              // API KEY
        @Path("START_INDEX") START_INDEX: Int, // 요청 시작 위치
        @Path("END_INDEX") END_INDEX: Int      // 요청 종료 위치
    ): Call<EducationData>
}