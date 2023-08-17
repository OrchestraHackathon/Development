package com.example.jonggangtime.Network

import com.example.jonggangtime.Data.LectureData
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String,
    @SerializedName("result") val result: ResultLogin
)

data class ResultLogin(
    @SerializedName("userId") val isSuccess: Boolean,
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)

data class ResponseCreateDefaultTimeTable(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String,
    @SerializedName("result") val result: ResultCreateDefaultTimeTable
)

data class ResultCreateDefaultTimeTable(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)

data class ResponseSignin(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String,
    @SerializedName("result") val result: ResultSignin
)

data class ResultSignin(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("nickname") val nickname: String
)

data class ResponseGetLectures(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String,
    @SerializedName("result") val result: ResultGetLectures
)

data class ResultGetLectures(
    @SerializedName("content") val content: ArrayList<LectureData>,
    @SerializedName("page") val page: Int,
    @SerializedName("isLast") val isLast: Boolean
)

data class ResponseCreateLecture(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String
)