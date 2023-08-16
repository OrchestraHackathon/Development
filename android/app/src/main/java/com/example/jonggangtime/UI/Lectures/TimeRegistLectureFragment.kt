package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.TimeData
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentTimeRegistLectureBinding
import com.islandparadise14.mintable.model.ScheduleEntity

class TimeRegistLectureFragment : BaseFragment<FragmentTimeRegistLectureBinding>(FragmentTimeRegistLectureBinding::inflate), View.OnClickListener, LectureTimeAdapter.OnItemClickListener {

    private val day = arrayOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
    private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()

    private var itemList : ArrayList<TimeData> = arrayListOf()

    lateinit var lectureTimeAdapter: LectureTimeAdapter

    override fun initAfterBinding() {
        initTimeTable()
    }

    private fun initTimeTable() {
        binding.timeTableMttv.initTable(day)
        binding.timeTableMttv.updateSchedules(scheduleList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registLectureCancelTv.setOnClickListener(this)
        binding.finishTv.setOnClickListener(this)
        binding.lectureTimePlusIv.setOnClickListener(this)

        lectureTimeAdapter = LectureTimeAdapter()
        lectureTimeAdapter.setBottomSheetListener(this)
        binding.lectureTimeRv.adapter = lectureTimeAdapter
        binding.lectureTimeRv.layoutManager = LinearLayoutManager(requireContext())

        val bottomSheetFragment = DetailLectureDialog()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.regist_lecture_cancel_tv -> {
                Log.d(TAG, "TimeRegistLectureFragment - 수강취소 클릭됨")
                parentFragmentManager.beginTransaction()
                    .replace(R.id.lecture_fl, SeekLecturesFragment())
                    .commitAllowingStateLoss()
            }
            R.id.finish_tv -> {
                Log.d(TAG, "TimeRegistLectureFragment - 완그 클릭됨")
                parentFragmentManager.beginTransaction()
                    .replace(R.id.lecture_fl, SeekLecturesFragment())
                    .commitAllowingStateLoss()
            }
            R.id.lecture_time_plus_iv -> {
                val item = TimeData("월요일", "13:00~15:00")
                itemList.add(item)
                lectureTimeAdapter.setData(item)
            }
        }
    }

    override fun onItemClicked(info: ArrayList<TimeData>) {
        itemList = info
    }

}