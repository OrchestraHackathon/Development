package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentTimeRegistLectureBinding
import com.islandparadise14.mintable.model.ScheduleEntity

class TimeRegistLectureFragment : BaseFragment<FragmentTimeRegistLectureBinding>(FragmentTimeRegistLectureBinding::inflate), View.OnClickListener, LectureTimeAdapter.OnItemClickListener, PickerDialog.OnPassData {

    private val day = arrayOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
    private var scheduleList: ArrayList<ScheduleEntity> = ArrayList()
    var firstState : Boolean = true
//    private var itemList : ArrayList<TimeData> = arrayListOf()

    lateinit var lectureTimeAdapter: LectureTimeAdapter

    val id_num = 0
    val name = "소프트웨어공학"
    val roomInfo = "공B352"
    val backgroundColor = "#73fcae68"
    val textColor = "#ffffff"

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
                Log.d(TAG, "TimeRegistLectureFragment - 완료 클릭됨")
//                TODO 시간에 대한 정보 전달
                parentFragmentManager.beginTransaction()
                    .replace(R.id.lecture_fl, SeekLecturesFragment())
                    .commitAllowingStateLoss()
            }
            R.id.lecture_time_plus_iv -> {
                Log.d(TAG, "1 : TimeRegistLectureFragment 에서 버튼이 클릭됨")
                val dialog = PickerDialog()
                dialog.setOnPassDataListener(this)
                dialog.isCancelable = false
                dialog.show(parentFragmentManager, dialog.tag)
            }
        }
    }

    override fun onItemClicked(info: ArrayList<ScheduleEntity>) {
        Log.d(TAG, "17 : TimeRegistLectureFragment 바뀐 데이터 받음\n" +
                "itemList : ${info.size}" )
        scheduleList.clear()
        for(i in 0 until info.size){
            scheduleList.add(info[i])
        }
//        scheduleList = info
        Log.d(TAG, "18 : TimeRegistLectureFragment - scheduleList 변경\n" +
                "scheduleList : ${scheduleList.size}")
        binding.timeTableMttv.updateSchedules(scheduleList)
        if(scheduleList.isEmpty()){
            binding.timeTableMttv.initTable(day)
        }
//        TODO 시간 데이터가 삭제된 경우
    }

    override fun onPassData(day: Int, startTime: String, endTime: String) {
        Log.d(TAG, "8 : TimeRegistLectureFragmnet 데이터를 받음\n" +
                "$day, $startTime, $endTime")
//        ScheduleDay
//        val days = arrayOf("일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일") -> 0~6
        val data = ScheduleEntity(id_num, name, roomInfo, day, startTime, endTime, backgroundColor, textColor)
        Log.d(TAG, "9 : TimeRegistLectureFragmnet 데이터를 전달\n" +
                "data : $data")
        lectureTimeAdapter.setData(data)
//        TODO 시간 데이터가 추가된 경우
        /*if(scheduleList.size>0){
            Log.d(TAG, "13/1 : TimeRegistLectureFragment - scheduleList 변경\n" +
                    "scheduleList : ${scheduleList[0].scheduleDay}")
        }*/
//        if(firstState){
            scheduleList.add(data)
//            firstState = false
//        }
        Log.d(TAG, "13 : TimeRegistLectureFragment - scheduleList 변경\n" +
                "scheduleList : ${scheduleList.size}")
        binding.timeTableMttv.updateSchedules(scheduleList)
    }

}