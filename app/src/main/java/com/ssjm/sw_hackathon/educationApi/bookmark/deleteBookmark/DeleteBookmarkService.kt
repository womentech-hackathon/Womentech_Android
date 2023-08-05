package com.ssjm.sw_hackathon.educationApi.bookmark.addBookmark

import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarkResponse
import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private var accessTokenValue: String = "Bearer " + GloabalApplication.prefs.getString("accessToken", "")
private var refreshTokenValue: String = GloabalApplication.prefs.getString("refreshToken", "")

interface DeleteBookmarkService {
    @POST("education/bookmarks/{bookmark_id}")
    fun deleteBookmark(
        @Header("Authorization") authorization: String = accessTokenValue, // 로그인으로 발급받은 AccessToken: JWT {발급받은 토큰} 형태로 입력
        @Header("refresh-token") refreshToken: String = refreshTokenValue, // 로그인으로 발급받은 RefreshToken
        @Path("bookmark_id") bookmark_id: Int
    ): Call<DeleteBookmarkResponse>
}