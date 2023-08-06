package com.ssjm.sw_hackathon.educationApi.bookmark

import android.content.ContentValues
import android.util.Log
import com.ssjm.sw_hackathon.apiClient.ApiClient
import com.ssjm.sw_hackathon.educationApi.bookmark.addBookmark.AddBookmarkResponse
import com.ssjm.sw_hackathon.educationApi.bookmark.addBookmark.AddBookmarkService
import com.ssjm.sw_hackathon.educationApi.bookmark.addBookmark.DeleteBookmarkResponse
import com.ssjm.sw_hackathon.educationApi.bookmark.addBookmark.DeleteBookmarkService
import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarkResponse
import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarkService
import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

// api 통신을 위한 retrofit
private val retrofit: Retrofit = ApiClient.getInstance()

// 북마크 조회
fun apiGetBookmark(
    getBookmark: (bookmarks: MutableList<GetBookmarks>?) -> Unit
) {
    retrofit.create(GetBookmarkService::class.java)
        .getBookmark()
        .enqueue(object : Callback<GetBookmarkResponse> {
            override fun onResponse(call: Call<GetBookmarkResponse>, response: Response<GetBookmarkResponse>) {
                Log.d(ContentValues.TAG, "교육 북마크 조회 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

                if(response.body() != null) {
                    getBookmark(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<GetBookmarkResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "교육 북마크 조회 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}

// 북마크 추가
fun apiAddBookmark(
    number: Int
) {
    retrofit.create(AddBookmarkService::class.java)
        .addBookmark(number = number)
        .enqueue(object : Callback<AddBookmarkResponse> {
            override fun onResponse(call: Call<AddBookmarkResponse>, response: Response<AddBookmarkResponse>) {
                Log.d(ContentValues.TAG, "교육 북마크 추가 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

            }

            override fun onFailure(call: Call<AddBookmarkResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "교육 북마크 추가 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}

// 북마크 삭제
fun apiDeleteBookmark(
    bookmarkId: Int
) {
    retrofit.create(DeleteBookmarkService::class.java)
        .deleteBookmark(bookmark_id = bookmarkId)
        .enqueue(object : Callback<DeleteBookmarkResponse> {
            override fun onResponse(call: Call<DeleteBookmarkResponse>, response: Response<DeleteBookmarkResponse>) {
                Log.d(ContentValues.TAG, "교육 북마크 삭제 결과 -------------------------------------------")
                Log.d(ContentValues.TAG, "onResponse: ${response.body().toString()}")

            }

            override fun onFailure(call: Call<DeleteBookmarkResponse>, t: Throwable) {
                Log.d(ContentValues.TAG, "교육 북마크 삭제 결과 fail -------------------------------------------")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
}