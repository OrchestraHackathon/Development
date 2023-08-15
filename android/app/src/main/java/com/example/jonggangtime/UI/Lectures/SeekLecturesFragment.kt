package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentLecturesBinding
import com.example.jonggangtime.databinding.FragmentSeekLecturesBinding

class SeekLecturesFragment : BaseFragment<FragmentSeekLecturesBinding>(FragmentSeekLecturesBinding::inflate), View.OnClickListener, LectureAdapter.OnItemClickListener {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lecturePlusFab.setOnClickListener(this)

        var lectureList = arrayListOf<LectureData>()
        for (i in 0..10){
            lectureList.add(LectureData("알바", "이주언", 1, 40, "아르바이트 시간 관리를 위한 과목 입니다 개인 알바 시간을 할당하여 과목을 수행하게 됩니다."))
        }

        val lectureAdapter = LectureAdapter(lectureList)
        lectureAdapter.setBottomSheetListener(this)
        binding.lectureListRv.adapter = lectureAdapter
        binding.lectureListRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.lecture_plus_fab -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.lecture_fl, ContentRegistLectureFragment())
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun onItemClicked(info: LectureData) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.lecture_fl, DetailLectureFragment())
            .commitAllowingStateLoss()
    }

}