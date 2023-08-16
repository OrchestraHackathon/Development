package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentTimeRegistLectureBinding
import com.islandparadise14.mintable.model.ScheduleEntity

class TimeRegistLectureFragment : BaseFragment<FragmentTimeRegistLectureBinding>(FragmentTimeRegistLectureBinding::inflate), View.OnClickListener {

    private val day = arrayOf("Sun", "Mon", "Tue", "Wen", "Thu", "Fri", "Sat")
    private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()

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
        }
    }

}