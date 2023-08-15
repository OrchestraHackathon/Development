package com.example.jonggangtime.Network

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

data class ResponseSignin(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String
)