package com.example.jonggangtime.UI.Friends

import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentFriendTimeTableBinding
import com.islandparadise14.mintable.model.ScheduleEntity

class FriendTimeTableFragment : BaseFragment<FragmentFriendTimeTableBinding>(FragmentFriendTimeTableBinding::inflate){

    private val day = arrayOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
    private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()

    override fun initAfterBinding() {
        initTimeTable()
    }

    private fun initTimeTable() {
        binding.timeTableView.initTable(day)
        binding.timeTableView.updateSchedules(scheduleList)
    }

}