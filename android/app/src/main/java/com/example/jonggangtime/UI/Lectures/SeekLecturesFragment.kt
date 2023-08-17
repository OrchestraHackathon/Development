package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.Network.ResponseGetLectures
import com.example.jonggangtime.Network.RetrofitClient
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentSeekLecturesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.PasswordAuthentication

class SeekLecturesFragment : BaseFragment<FragmentSeekLecturesBinding>(FragmentSeekLecturesBinding::inflate), View.OnClickListener, LectureAdapter.OnItemClickListener, MainActivity.onBackPressedListener {

    private var backPressedTime: Long = 0
    lateinit var lectureAdapter: LectureAdapter
    var page = 0
    var isLast = false

    override fun initAfterBinding() {
        Log.d(TAG, "SeekLectureFragment - onViewCreated()")
        binding.lecturePlusFab.setOnClickListener(this)

        /*val lectureList = arrayListOf<LectureData>()
        for (i in 0..10){
            lectureList.add(LectureData(i, "알바 $i", "이주언", 1, 40, "아르바이트 시간 관리를 위한 과목 입니다 개인 알바 시간을 할당하여 과목을 수행하게 됩니다."))
        }
        val lectureAdapter = LectureAdapter()
        lectureAdapter.setBottomSheetListener(this)
        binding.lectureListRv.adapter = lectureAdapter
        binding.lectureListRv.layoutManager = LinearLayoutManager(requireContext())
        lectureAdapter.setList(lectureList)*/

        RetrofitClient.instance.getLectures().enqueue(object: Callback<ResponseGetLectures>{
            override fun onResponse(
                call: Call<ResponseGetLectures>,
                response: Response<ResponseGetLectures>
            ) {
                if(response.isSuccessful){
                    Log.d(TAG, "SeekLecturesFragment - Retrofit getLectures() 실행결과 - 성공")
                    page = response.body()!!.result.page
                    isLast = response.body()!!.result.isLast

                    lectureAdapter = LectureAdapter(requireContext())
                    lectureAdapter.setBottomSheetListener(this@SeekLecturesFragment)
                    binding.lectureListRv.adapter = lectureAdapter
                    binding.lectureListRv.layoutManager = LinearLayoutManager(requireContext())
                    lectureAdapter.setList(response.body()!!.result.content)
                } else{
                    Log.d(TAG, "SeekLecturesFragment - Retrofit getLectures() 실행결과 - 안좋음")
                }
            }

            override fun onFailure(call: Call<ResponseGetLectures>, t: Throwable) {
                Log.d(TAG, "SeekLecturesFragment - Retrofit getLectures() 실행결과 - 실패\n" +
                        "t : $t")
            }

        })

        binding.lectureListRv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!recyclerView.canScrollVertically(1)){   //스크롤이 끝까지 갔는지 체크
                    /*lectureAdapter.deleteLoading()
                    lectureAdapter.setList(lectureList)*/
                    if(!isLast){
                        page++
                        RetrofitClient.instance.getLectures(page = page).enqueue(object: Callback<ResponseGetLectures>{
                            override fun onResponse(
                                call: Call<ResponseGetLectures>,
                                response: Response<ResponseGetLectures>
                            ) {
                                if(response.isSuccessful){
                                    Log.d(TAG, "SeekLecturesFragment - Retrofit getLectures() 실행결과 - 성공")
                                    page = response.body()!!.result.page
                                    isLast = response.body()!!.result.isLast

                                    lectureAdapter.deleteLoading()
                                    lectureAdapter.setList(response.body()!!.result.content)
                                } else{
                                    Log.d(TAG, "SeekLecturesFragment - Retrofit getLectures() 실행결과 - 안좋음")
                                }
                            }

                            override fun onFailure(call: Call<ResponseGetLectures>, t: Throwable) {
                                Log.d(TAG, "SeekLecturesFragment - Retrofit getLectures() 실행결과 - 실패\n" +
                                        "t : $t")
                            }

                        })
                    } else{
                        lectureAdapter.deleteLoading()
                    }
                }
                else if(!recyclerView.canScrollVertically(-1)){ //스크롤이 최상단까지 갔는지 체크

                }
                /* 위의 코드가 오류가 날 경우 한번 시도
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() // 화면에 보이는 마지막 아이템의 position
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1 // 어댑터에 등록된 아이템의 총 개수 -1

                // 스크롤이 끝에 도달했는지 확인
                if (lastVisibleItemPosition == itemTotalCount) {

                }*/
            }
        })
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
        val bundle = Bundle()
        bundle.putParcelable("data", info)
        val fragment = DetailLectureFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.lecture_fl, fragment)
            .addToBackStack("seekLectures")
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if(this.childFragmentManager.backStackEntryCount>=1){
            this.childFragmentManager.popBackStackImmediate()
            binding.lecturesSearchEt.clearFocus()
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SeekLectureFragment - onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SeekLectureFragment - onResume()")
    }

}