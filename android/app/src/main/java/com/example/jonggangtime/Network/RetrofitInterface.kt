package com.example.jonggangtime.Network

import com.example.jonggangtime.Data.LoginInfo
import com.example.jonggangtime.Data.SigninInfo
import retrofit2.Call
import retrofit2.http.Body
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