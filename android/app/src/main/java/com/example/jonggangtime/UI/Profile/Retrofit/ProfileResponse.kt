package com.example.jonggangtime.UI.Profile.Retrofit

import com.google.gson.annotations.SerializedName

//추후 api 개발에 따라 변경 예정 - 수강완료 과목 리스트 조회 결과
data class CompletedLecture(
    var courseId: Int,
    var courseName: String? = "",
    var professor: String? = "",
    var categoryName: String? = "",
    var courseDetails: String? = "",
    var registerPeople: String? = "",
    var flag : Boolean
)


data class ResponseMyPage(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String,
    @SerializedName("result") val result: ResultMyPage
)

data class ResultMyPage(
    @SerializedName("email") val email: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userNickName") val userNickName: String,
    @SerializedName("aboutMe") val aboutMe: String,
    @SerializedName("profileImageUrl") val profileImageUrl: String,
)