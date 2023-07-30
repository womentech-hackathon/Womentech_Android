package com.ssjm.sw_hackathon.education.recycler

interface EducationItemInterface {
    val applicationPeriod: String // 신청 기간
    val educationPeriod: String   // 교육 기간
}
data class EducationItem(
    val status: String, // 모집중 or 마감
    val title: String,  // 교육 제목
    override val applicationPeriod: String, // 신청 기간
    override val educationPeriod: String,   // 교육 기간
    var isBookmark: Boolean        // 찜 유무
) : EducationItemInterface

data class LoadingItem(
    override val applicationPeriod: String, // 신청 기간
    override val educationPeriod: String,   // 교육 기간
) : EducationItemInterface
