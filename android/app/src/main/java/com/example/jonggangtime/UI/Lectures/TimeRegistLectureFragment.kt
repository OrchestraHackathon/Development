package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.FragmentDetailLectureBinding
import com.example.jonggangtime.databinding.FragmentTimeRegistLectureBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class TimeRegistLectureFragment : BaseFragment<FragmentTimeRegistLectureBinding>(FragmentTimeRegistLectureBinding::inflate), View.OnClickListener {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registLectureCancelTv.setOnClickListener(this)
        binding.finishTv.setOnClickListener(this)

        val bottomSheetFragment = DetailLectureDialog()
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.regist_lecture_cancel_tv -> {
                Log.d(TAG, "TimeRegistLectureFragment - 수강취소 클릭됨")
                parentFragmentManager.beginTransaction()
                    .replace(R.id.lecture_fl, SeekLecturesFragment())
                    .commitAllowingStateLoss()
            }
            R.id.finish_tv -> {
                Log.d(TAG, "TimeRegistLectureFragment - 완그 클릭됨")
                parentFragmentManager.beginTransaction()
                    .replace(R.id.lecture_fl, SeekLecturesFragment())
                    .commitAllowingStateLoss()
            }
        }
    }

}