package com.example.jonggangtime.UI.Profile

import android.content.Intent
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
            override fun onItemClick(
                data: CompletedLecture,
                holder: CompletedLecturesRVAdapter.ViewHolder
            ) {
                if (holder.binding.completedLectureScoreTv.text == "성적 평가"){
                    // 성적평가 다이얼로그
                } else{
                    val intent = Intent(activity, CertificateActivity::class.java)
                    startActivity(intent)
                }
            }

        })
        binding.completedLecturesRv.adapter = completedLecturesAdapter

    }
}