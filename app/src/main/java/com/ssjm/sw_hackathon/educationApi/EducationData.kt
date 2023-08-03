package com.ssjm.sw_hackathon.educationApi

import com.google.gson.annotations.SerializedName

// 서울시 어르신 취업지원센터 교육 정보 api
data class EducationData(
    @SerializedName("tbViewProgram")
    val tbViewProgram: TbViewProgram,
)

data class TbViewProgram(
    @SerializedName("list_total_count") // 총 데이터 건수 (정상조회 시 출력됨)
    val list_total_count: Int,

    @SerializedName("RESULT")
    val RESULT: RESULT,

    @SerializedName("row")
    val row: MutableList<EducationRow>,
)

data class RESULT(
    @SerializedName("CODE")    // 요청결과 코드
    val CODE: String,

    @SerializedName("MESSAGE") // 요청결과 메시지
    val MESSAGE: String,
)

// 교육 데이터
data class EducationRow(
    @SerializedName("IDX")      // 교육넘버
    val IDX: String,

    @SerializedName("SUBJECT")  // 교육명
    val SUBJECT: String,

    @SerializedName("STARTDATE") // 교육시작일
    val STARTDATE: String,

    @SerializedName("ENDDATE")   // 교육종료일
    val ENDDATE: String,

    @SerializedName("APPLICATIONSTARTDATE") // 교육신청시작일
    val APPLICATIONSTARTDATE: String,

    @SerializedName("APPLICATIONENDDATE")   // 교육신청종료일
    val APPLICATIONENDDATE: String,

    @SerializedName("REGISTPEOPLE") // 수강정원
    val REGISTPEOPLE: String,

    @SerializedName("REGISTCOST")   // 교육비용
    val REGISTCOST: String,

    @SerializedName("APPLY_STATE")  // 교육상태
    val APPLY_STATE: String,

    @SerializedName("VIEWDETAIL")   // 강좌상세화면
    val VIEWDETAIL: String,
)