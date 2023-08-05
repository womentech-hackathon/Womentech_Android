package com.ssjm.sw_hackathon.goal.recycler

import java.time.LocalDate

data class DayOfWeekItem(
    var date: LocalDate,   // 날짜
    var day: Int,          // 일
    var dayOfWeek: String, // 요일
    var progress: String,  // 진행도: done, some, fail, none
    var selected: Boolean = false, // 선택 유무
)
