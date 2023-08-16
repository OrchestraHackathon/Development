package com.example.jonggangtime.Network

import com.example.jonggangtime.Data.LoginInfo
import com.example.jonggangtime.Data.RegistLectureData
import com.example.jonggangtime.Data.SigninInfo
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @POST("users/login")
    fun login(
        @Body loginInfo: LoginInfo
    ): Call<ResponseLogin>

    @POST("users/sign-up")
    fun signin(
        @Body signinInfo: SigninInfo
    ): Call<ResponseSignin>

    @GET("courses")
    fun getLectures(
        @Query("page") page: Int? = null,
        @Query("size") size : Int? = null
    ): Call<ResponseGetLectures>

    @POST("time-table/{usersId}")
    fun createDefaultTimeTable(
        @Path("usersId") id: Long
    ): Call<ResponseCreateDefaultTimeTable>

    @POST("courses")
    fun creatLecture(
        @Header("Authorization") auth: String,
        @Body content: RegistLectureData
    ): Call<ResponseCreateLecture>
}