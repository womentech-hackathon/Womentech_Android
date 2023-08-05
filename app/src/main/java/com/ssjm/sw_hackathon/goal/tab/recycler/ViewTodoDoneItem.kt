package com.ssjm.sw_hackathon.goal.tab.recycler

data class ViewTodoDoneItem(
    val title: String,
    val days: MutableList<String>,
    val startDate: String,
    val finishDate: String,
)
