package com.example.jonggangtime.UI.TimeTable.Retrofit

import android.util.Log
import com.example.jonggangtime.UI.Profile.Retrofit.ProfileMyPageView
import com.example.jonggangtime.UI.Profile.Retrofit.ProfileRetrofitInterface
import com.example.jonggangtime.UI.Profile.Retrofit.ResponseMyPage
import com.example.jonggangtime.Utils.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeTableService {
    private lateinit var timeTableView: TimeTableView

    fun setmyTimeTableiew(timeTableView: TimeTableView) {
        this.timeTableView = timeTableView
    }

    private val timeTableService = NetworkModule.getInstance()?.create(TimeTableRetrofitInterface::class.java)

    fun getMyTimeTable(){
        timeTableService?.myTimeTable()?.enqueue(object : Callback<ResponseTimeTable> {

            override fun onResponse(
                call: Call<ResponseTimeTable>,
                response: Response<ResponseTimeTable>
            ) {
                Log.d("timeTableService", "onResponse 호출됨")
                if (response.isSuccessful && response.code() == 200) {
                    Log.d("timeTableService", "성공")
                    val resp: ResponseTimeTable = response.body()!!
                    timeTableView.myTimeTableSuccess(resp.result!!)
                }
            }

            override fun onFailure(call: Call<ResponseTimeTable>, t: Throwable) {
                Log.d("timeTableService", "onFailure 호출됨")
            }

        })
    }
}