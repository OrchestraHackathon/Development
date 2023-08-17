package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentDetailLectureBinding

class DetailLectureFragment : BaseFragment<FragmentDetailLectureBinding>(FragmentDetailLectureBinding::inflate), MainActivity.onBackPressedListener {

    override fun initAfterBinding() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = requireArguments().getParcelable<LectureData>("data") ?: LectureData(1, "test1", "박지원", "취", 4, "ㅇㅇ")
        Log.d(TAG, "data : $data")

        binding.titleTv.text = data.lectureName
        binding.lectureProfessorTv.text = "교수 : " + data.professorName
        binding.lectureMiniContentInputTv.text = data.shortContent
        binding.lectureContentInputTv.text = data.shortContent
        binding.lectureCountTv.text = "${data.nums}명이 이 과목을 수강중입니다."

        val nameList = arrayListOf("스터디", "자격증", "루틴", "운동", "알바", "취미")
        val itemList = arrayListOf<LectureCategoryData>()
        itemList.add(LectureCategoryData(nameList.indexOf(data.category), data.category))

        val categoryAdapter = CategoryAdapter(requireContext(), arrayListOf(LectureCategoryData(1, "스터디")), 0)
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