package com.example.jonggangtime.UI.TimeTable.Retrofit

import com.example.jonggangtime.UI.Profile.Retrofit.ResultMyPage
import com.google.gson.annotations.SerializedName

data class TimeTableSchedule(
    var day: Int,
    var startTime: String? = "",
    var endTime: String? = "",
    var courseName: String? = "",
    var courseProfessor: String? = "",
    var courseId: Int
)


data class ResponseTimeTable(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("responseCode") val code: Int,
    @SerializedName("responseMessage") val msg: String,
    @SerializedName("result") val result: ResultTimeTable
)

data class ResultTimeTable(
    @SerializedName("timeTableName") val timeTableName: String,
    @SerializedName("courseInfos") val courseInfos: ArrayList<TimeTableSchedule>,
)