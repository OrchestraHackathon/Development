package com.example.jonggangtime.Network

import com.example.jonggangtime.Data.LoginInfo
import com.example.jonggangtime.Data.SigninInfo
import com.example.jonggangtime.UI.Profile.Retrofit.ResponseMyPage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitInterface {
    @POST("users/login")
    fun login(
        @Body loginInfo: LoginInfo
    ): Call<ResponseLogin>

    @POST("users/sign-up")
    fun signin(
        @Body signinInfo: SigninInfo
    ): Call<ResponseSignin>
}