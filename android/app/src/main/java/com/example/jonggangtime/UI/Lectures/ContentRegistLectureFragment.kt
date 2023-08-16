package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentContentRegistLectureBinding

class ContentRegistLectureFragment : BaseFragment<FragmentContentRegistLectureBinding>(FragmentContentRegistLectureBinding::inflate), RegistDialog.OnItemClickListener, CategoryAdapter.OnItemClickListener {

    lateinit var categoryState: ArrayList<Boolean>

    override fun initAfterBinding() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameList = arrayListOf("스터디", "자격증", "루틴", "운동", "알바", "취미")
        val itemList = arrayListOf<LectureCategoryData>()
        for(i in 0..5){
            itemList.add(LectureCategoryData(i, nameList[i]))
        }

        val categoryAdapter = CategoryAdapter(requireContext(), itemList, 1)
        categoryAdapter.setBottomSheetListener(this)
        binding.lectureCategoryRv.adapter = categoryAdapter
        binding.lectureCategoryRv.layoutManager = LinearLayoutManager(requireContext()).also { it.orientation = LinearLayoutManager.HORIZONTAL }


        binding.registBtn.setOnClickListener {
            val dialog = RegistDialog()
            dialog.setBottomSheetListener(this)
            dialog.isCancelable = false
            dialog.show(parentFragmentManager, "registDialog")
        }
        binding.closeIv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.lecture_fl, SeekLecturesFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onItemClicked(result: Boolean) {
        if(result){ //yes 일 경우 -> Time정하는 곳으로 이동
            parentFragmentManager.beginTransaction()
                .replace(R.id.lecture_fl, TimeRegistLectureFragment())
                .commitAllowingStateLoss()
        } else {    //no 일 경우 -> 어디로 이동?
            parentFragmentManager.beginTransaction()
                .replace(R.id.lecture_fl, SeekLecturesFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onItemClicked(info: ArrayList<Boolean>) {
        categoryState = info
    }

}