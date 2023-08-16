package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.Data.RegistLectureData
import com.example.jonggangtime.MyApplication
import com.example.jonggangtime.Network.ResponseCreateLecture
import com.example.jonggangtime.Network.RetrofitClient
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentContentRegistLectureBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentRegistLectureFragment : BaseFragment<FragmentContentRegistLectureBinding>(FragmentContentRegistLectureBinding::inflate), RegistDialog.OnAnswerClickListener, CategoryAdapter.OnItemClickListener, MainActivity.onBackPressedListener {

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
            val index = arrayListOf<Int>()
            for(i in 0 until categoryState.size){
                if(categoryState[i]){
                    index.add(i+1)
                }
            }

            val content = RegistLectureData(binding.lectureNameInputTf.text.toString(), index ,binding.lectureMiniContentTf.text.toString(), binding.lectureContentTf.text.toString())
            Log.d(TAG, "ContentRegistLectureFragment - 등록하기 버튼 클릭\n" +
                    "name : ${content.courseName}\n" +
                    "category : ${content.categories}\n" +
                    "mini : ${content.courseSummary}\n" +
                    "content : ${content.courseDetail}")
            RetrofitClient.instance.creatLecture("Bearer ${MyApplication.prefs.getString("accessToken", "")}", content).enqueue(object: Callback<ResponseCreateLecture>{
                override fun onResponse(
                    call: Call<ResponseCreateLecture>,
                    response: Response<ResponseCreateLecture>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "ContentRegistLectureFragment - Retrofit creatLecture() 실행결과 - 성공")

                        val dialog = RegistDialog(0)
                        dialog.setBottomSheetListener(this@ContentRegistLectureFragment)
                        dialog.isCancelable = false
                        dialog.show(parentFragmentManager, "registDialog")
                    }else {
                        Log.d(TAG, "ContentRegistLectureFragment - Retrofit creatLecture() 실행결과 - 안좋음")
                    }
                }

                override fun onFailure(call: Call<ResponseCreateLecture>, t: Throwable) {
                    Log.d(TAG, "ContentRegistLectureFragment - Retrofit creatLecture() 실행결과 - 실패\n" +
                            "t : $t")
                }

            })
        }
        binding.closeIv.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.lecture_fl, SeekLecturesFragment())
                .commitAllowingStateLoss()
        }
    }

    override fun onAnswerClicked(result: Boolean) {
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

    override fun onBackPressed() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.lecture_fl, SeekLecturesFragment())
            .commitAllowingStateLoss()
    }

}