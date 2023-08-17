package com.example.jonggangtime.UI.TimeTable.Retrofit

import com.example.jonggangtime.UI.Profile.Retrofit.ResultMyPage

interface TimeTableView {
    fun myTimeTableSuccess(result: ResultTimeTable)
    fun myTimeTableFailure(code: Int, message: String)
}
