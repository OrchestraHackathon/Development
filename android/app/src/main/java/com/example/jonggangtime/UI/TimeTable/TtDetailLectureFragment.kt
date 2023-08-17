package com.example.jonggangtime.UI.TimeTable

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Lectures.CategoryAdapter
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentDetailLectureBinding

class TtDetailLectureFragment : BaseFragment<FragmentDetailLectureBinding> (FragmentDetailLectureBinding::inflate), MainActivity.onBackPressedListener {

    override fun initAfterBinding() {
        initCategory()
        binding.registBtn.text = "수강 완료하기"
        initClickListener()
    }

    private fun initCategory() {
        val nameList = arrayListOf("스터디", "자격증", "루틴", "운동", "알바", "취미")
        val itemList = arrayListOf<LectureCategoryData>()
        for(i in 0..5){
            itemList.add(LectureCategoryData(i, nameList[i]))
        }
        val categoryAdapter = CategoryAdapter(requireContext(), itemList, 0)
        binding.lectureCategoryRv.adapter = categoryAdapter
        binding.lectureCategoryRv.layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }
    }

    private fun initClickListener() {
        binding.closeIv.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fl, TimeTableFragment())
                .commit()
        }

        binding.registBtn.setOnClickListener {
            //TODO: 수강 완료 api 연결
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fl, TimeTableFragment())
                .commit()
            showToast("수강이 완료되었습니다")
        }
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_fl, TimeTableFragment())
            .commit()
    }
}