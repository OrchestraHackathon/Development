package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentDetailLectureBinding

class DetailLectureFragment : BaseFragment<FragmentDetailLectureBinding>(FragmentDetailLectureBinding::inflate), MainActivity.onBackPressedListener {

    override fun initAfterBinding() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameList = arrayListOf("스터디", "자격증", "루틴", "운동", "알바", "취미")
        val itemList = arrayListOf<LectureCategoryData>()
        for(i in 0..5){
            itemList.add(LectureCategoryData(i, nameList[i]))
        }

        val categoryAdapter = CategoryAdapter(requireContext(), itemList, 0)
        binding.lectureCategoryRv.adapter = categoryAdapter
        binding.lectureCategoryRv.layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }

        binding.registBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.lecture_fl, TimeRegistLectureFragment())
                .commitAllowingStateLoss()
        }
        binding.closeIv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.lecture_fl, SeekLecturesFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.lecture_fl, SeekLecturesFragment())
            .commitAllowingStateLoss()
    }

}