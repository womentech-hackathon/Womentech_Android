package com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark

import com.google.gson.annotations.SerializedName

data class GetBookmarkResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: MutableList<GetBookmarks>,
)

data class GetBookmarks(
    @SerializedName("id")
    val id: Int,

    @SerializedName("number")
    val number: Int,
)