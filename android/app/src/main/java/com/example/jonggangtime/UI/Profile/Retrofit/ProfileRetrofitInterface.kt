package com.example.jonggangtime.UI.Profile.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileRetrofitInterface {
    @GET("users/me")
    fun mypage(
    ): Call<ResponseMyPage>
}