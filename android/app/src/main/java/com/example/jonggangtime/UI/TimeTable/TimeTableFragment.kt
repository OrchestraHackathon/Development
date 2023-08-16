package com.example.jonggangtime.UI.TimeTable

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Friends.FriendTimeTableFragment
import com.example.jonggangtime.UI.Friends.FriendsRVAdapter
import com.example.jonggangtime.UI.Friends.Retrofit.Friend
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentTimeTableBinding
import com.islandparadise14.mintable.MinTimeTableView
import com.islandparadise14.mintable.model.ScheduleDay
import com.islandparadise14.mintable.model.ScheduleEntity
import com.islandparadise14.mintable.tableinterface.OnScheduleClickListener
import com.islandparadise14.mintable.tableinterface.OnTimeCellClickListener

class TimeTableFragment : BaseFragment<FragmentTimeTableBinding>(FragmentTimeTableBinding::inflate) {

    private val day = arrayOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
    private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()

    private lateinit var friendsRVAdapter: FriendsRVAdapter
    private var friendsArray = java.util.ArrayList<Friend>()

    override fun initAfterBinding() {
        initAdapter()
        initTimeTable()
        initFriends()
    }

    private fun initAdapter() {
        friendsRVAdapter = FriendsRVAdapter(friendsArray)
        binding.timeTableFriendsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        friendsRVAdapter.setOnItemClickListener(object : FriendsRVAdapter.OnItemClickListener{
            override fun onItemClick(data: Friend) {
                Log.d("timetable", data.friendName + "클릭됨")
                requireActivity().supportFragmentManager.beginTransaction().addToBackStack("friends").replace(
                    R.id.main_fl,
                    FriendTimeTableFragment()
                ).commit()
            }
        })
        binding.timeTableFriendsRv.adapter = friendsRVAdapter

    }

    private fun initFriends() {
        // TEST용 더미 데이터
        friendsArray.apply {
            add(Friend(1, "이준영"))
            add(Friend(2, "박지원"))
            add(Friend(3, "이주언"))
            add(Friend(4, "남보우"))
            add(Friend(5, "정재연"))
            add(Friend(6, "홍길동1"))
            add(Friend(7, "홍길동2"))
            add(Friend(8, "홍길동3"))
            add(Friend(9, "홍길동4"))
        }
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
        schedule.setOnClickListener {
            //do something
            Log.d("schedule", "1")
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.main_fl, TtDetailLectureFragment())
                .commitAllowingStateLoss()
        }
        scheduleList.add(schedule2)
        scheduleList.add(schedule3)

        binding.timeTableView.initTable(day)
        binding.timeTableView.updateSchedules(scheduleList)
    }

}