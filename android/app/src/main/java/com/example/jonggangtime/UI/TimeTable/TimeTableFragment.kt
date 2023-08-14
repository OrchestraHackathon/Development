package com.example.jonggangtime.UI.TimeTable

import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentTimeTableBinding
import com.islandparadise14.mintable.MinTimeTableView
import com.islandparadise14.mintable.model.ScheduleDay
import com.islandparadise14.mintable.model.ScheduleEntity

class TimeTableFragment : BaseFragment<FragmentTimeTableBinding>(FragmentTimeTableBinding::inflate) {

    private val day = arrayOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
    private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()

    override fun initAfterBinding() {
        initTimeTable()
    }

    private fun initTimeTable() {
        // TEST용 더미 데이터
        val schedule = ScheduleEntity(
            32, //originId
            "Database", //scheduleName
            "IT Building 301", //roomInfo
            ScheduleDay.TUESDAY, //ScheduleDay object (MONDAY ~ SUNDAY)
            "10:20", //startTime format: "HH:mm"
            "12:30", //endTime  format: "HH:mm"
            "#73fcae68", //backgroundColor (optional)
            "#000000" //textcolor (optional)
        )

        val schedule2 = ScheduleEntity(
            33, //originId
            "Database", //scheduleName
            "홍길동", //roomInfo
            ScheduleDay.SATURDAY, //ScheduleDay object (MONDAY ~ SUNDAY)
            "14:20", //startTime format: "HH:mm"
            "17:30", //endTime  format: "HH:mm"
        )

        val schedule3 = ScheduleEntity(
            34, //originId
            "Database", //scheduleName
            "홍길동", //roomInfo
            ScheduleDay.WEDNESDAY, //ScheduleDay object (MONDAY ~ SUNDAY)
            "14:20", //startTime format: "HH:mm"
            "15:30", //endTime  format: "HH:mm"
        )

        //schedule2,3처럼 색 지정을 안해주면 동일 색상(배경:회색, 글씨:흰색)으로 들어간다.

        scheduleList.add(schedule)
        scheduleList.add(schedule2)
        scheduleList.add(schedule3)

        binding.timeTableView.initTable(day)
        binding.timeTableView.updateSchedules(scheduleList)
    }

}