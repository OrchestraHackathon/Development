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
            CompletedLecture(5, "아르바이트", "이주언", "아르바이트", "아르바이트 시간 관리를 위한 과목입니다. 개인 알바 시간입니다.", "5명", false),
        )
        completedLectureArray.add(
            CompletedLecture(3, "문학감상 및 분석", "남보우", "루틴", "매일 세계 명작 소설들을 읽고 그 시대의 역사에 대해 알아보아요", "15명", true),
        )
        completedLectureArray.add(
            CompletedLecture(4, "식단과 운동", "박지원", "운동", "같이 1시간 30분씩 모여서 한강을 뛰어요", "20명", true),
        )
        completedLectureArray.add(
            CompletedLecture(3, "클라이밍", "남보우", "운동", "한강 공원 클라이밍장에서 모여 같이 클라이밍에 대해 알아보아요", "2명", true),
        )
    }

    private fun initAdapter() {
        completedLecturesAdapter = CompletedLecturesRVAdapter(requireContext(), completedLectureArray)
        binding.completedLecturesRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        completedLecturesAdapter.setOnItemClickListener(object : CompletedLecturesRVAdapter.OnItemClickListener{
            override fun onItemClick(
                data: CompletedLecture,
                holder: CompletedLecturesRVAdapter.ViewHolder
            ) {
                if (holder.binding.completedLectureScoreTv.text == "성적 평가"){
                    // 성적평가 다이얼로그
                    val dialogScoreEvaluation = DialogScoreEvaluation()
                    dialogScoreEvaluation.show(parentFragmentManager, "CustomDialog")
                } else{
                    val intent = Intent(activity, CertificateActivity::class.java)
                    startActivity(intent)
                }
            }

        })
        binding.completedLecturesRv.adapter = completedLecturesAdapter

    }
}