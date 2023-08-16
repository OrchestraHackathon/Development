package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentSeekLecturesBinding

class SeekLecturesFragment : BaseFragment<FragmentSeekLecturesBinding>(FragmentSeekLecturesBinding::inflate), View.OnClickListener, LectureAdapter.OnItemClickListener, MainActivity.onBackPressedListener {

    private var backPressedTime: Long = 0

    override fun initAfterBinding() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lecturePlusFab.setOnClickListener(this)

        val lectureList = arrayListOf<LectureData>()
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
                    .addToBackStack("seekLectures")
                    .commitAllowingStateLoss()
            }
        }
    }

    override fun onItemClicked(info: LectureData) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.lecture_fl, DetailLectureFragment())
            .addToBackStack("seekLectures")
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if(this.childFragmentManager.backStackEntryCount>=1){
            this.childFragmentManager.popBackStackImmediate()
            binding.searchTf.clearFocus()
        }

        if (this.isVisible){
            // 2초안에 뒤로가기 2번 누르면 종료
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                requireActivity().finish()
            } else {
                showToast( "한번 더 누르면 종료됩니다.")
            }
            backPressedTime = System.currentTimeMillis()
        }
    }

}