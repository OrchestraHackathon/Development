package com.example.jonggangtime.Data

import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String
)

data class SigninInfo(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String,
    @SerializedName("userName") var name : String,
    @SerializedName("userNickName") var nickname: String
)

data class LectureData(
    val lectureName: String,
    val professorName: String,
    val category: Int,
    val nums: Int,
    val shortContent: String
)

data class DetailLectureData(
    val lectureName: String,
    val professorName: String,
    val category: Int,
    val nums: Int,
    val shortContent: String,
    val Content: String
)

data class SeekFriendData(
    val nickname: String,
    val name: String
)

data class LectureCategoryData(
    val categoryId : Int,
    val categoryName : String
)