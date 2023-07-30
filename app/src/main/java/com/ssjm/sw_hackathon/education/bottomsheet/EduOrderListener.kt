package com.ssjm.sw_hackathon.education.bottomsheet

interface EduOrderListener {
    fun orderNew() // 최신순 정렬

    fun orderOld() // 오래된순 정렬

    fun orderEnd() // 마감순 정렬
}