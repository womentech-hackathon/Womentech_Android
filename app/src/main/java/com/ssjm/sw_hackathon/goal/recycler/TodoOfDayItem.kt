package com.ssjm.sw_hackathon.goal.recycler

import java.time.LocalDate

data class TodoOfDayItem(
    var date: LocalDate,   // 날짜
    var content: String,   // 할 일 내용
    var checked: Boolean,  // 체크 유무
)
