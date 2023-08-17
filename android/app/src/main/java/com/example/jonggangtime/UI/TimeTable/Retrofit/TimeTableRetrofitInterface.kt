package com.example.jonggangtime.UI.TimeTable.Retrofit

import com.example.jonggangtime.UI.Profile.Retrofit.ResponseMyPage
import retrofit2.http.GET
import retrofit2.Call

interface TimeTableRetrofitInterface {
    @GET("time-table")
    fun myTimeTable(
    ): Call<ResponseTimeTable>
}