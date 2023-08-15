package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentContentRegistLectureBinding
import com.example.jonggangtime.databinding.FragmentDetailLectureBinding

class DetailLectureFragment : BaseFragment<FragmentDetailLectureBinding>(FragmentDetailLectureBinding::inflate) {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameList = arrayListOf("스터디", "자격증", "루틴", "운동", "알바", "취미")
        val itemList = arrayListOf<LectureCategoryData>()
        for(i in 0..5){
            itemList.add(LectureCategoryData(i, nameList[i]))
        }

        val categoryAdapter = CategoryAdapter(itemList, 0)
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

}