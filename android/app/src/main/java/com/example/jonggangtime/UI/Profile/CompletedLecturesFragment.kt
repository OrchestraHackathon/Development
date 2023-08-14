package com.example.jonggangtime.UI.Profile

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.UI.Profile.Retrofit.CompletedLecture
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentCompletedLecturesBinding
import java.util.ArrayList

class CompletedLecturesFragment : BaseFragment<FragmentCompletedLecturesBinding>(FragmentCompletedLecturesBinding::inflate)   {

    private lateinit var completedLecturesAdapter: CompletedLecturesRVAdapter
    private var completedLectureArray = ArrayList<CompletedLecture>()

    override fun initAfterBinding() {
        initAdapter()

        //테스트용 임시 코드
        completedLectureArray.add(
            CompletedLecture(1, "해커톤", "prof", "스터디", "위캔 두잇", "5명", false),
        )
        completedLectureArray.add(
            CompletedLecture(2, "해커톤2", "prof", "스터디", "위캔 두잇", "5명", true),
        )
    }

    private fun initAdapter() {
        completedLecturesAdapter = CompletedLecturesRVAdapter(completedLectureArray)
        binding.completedLecturesRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        completedLecturesAdapter.setOnItemClickListener(object : CompletedLecturesRVAdapter.OnItemClickListener{
            override fun onItemClick(data: CompletedLecture) {
                //TODO: 클릭했을 때 무슨 행동을 할지
            }
        })
        binding.completedLecturesRv.adapter = completedLecturesAdapter

    }
}