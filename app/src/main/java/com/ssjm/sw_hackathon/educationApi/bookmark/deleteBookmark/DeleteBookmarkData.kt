package com.ssjm.sw_hackathon.educationApi.bookmark.addBookmark

import com.google.gson.annotations.SerializedName

data class DeleteBookmarkResponse (
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: String?,
)