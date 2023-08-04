package com.ssjm.sw_hackathon.home.recycler

import android.graphics.drawable.Drawable

data class HomeTodoItem(
    val coverImage: String,
    val content: String,
    val period: String,
    val day: MutableList<String>
)
