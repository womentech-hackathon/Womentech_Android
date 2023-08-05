package com.ssjm.sw_hackathon.education.recycler

interface EducationItemInterface {
    val status: String           // 모집중 or 마감
    val applicationStart: String // 신청 시작일
    val applicationEnd: String   // 신청 마감일
}
data class EducationItem(
    val bookmarkId: Int? = null,
    val eduNumber: Int,
    override val status: String, // 모집중 or 마감
    val title: String,  // 교육 제목
    val applicationPeriod: String, // 신청 기간
    val educationPeriod: String,   // 교육 기간
    override val applicationStart: String,  // 신청 시작일
    override val applicationEnd: String,    // 신청 마감일
    var isBookmark: Boolean        // 찜 유무
) : EducationItemInterface

data class LoadingItem(
    override val status: String = "가가", // 모집중 or 마감
    override val applicationStart: String = "하하",  // 신청 시작일
    override val applicationEnd: String = "하하",    // 신청 마감일
) : EducationItemInterface
