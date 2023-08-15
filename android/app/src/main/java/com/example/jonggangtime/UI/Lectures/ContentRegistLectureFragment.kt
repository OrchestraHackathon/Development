package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentContentRegistLectureBinding
import com.example.jonggangtime.databinding.FragmentSeekListFriendsBinding

class ContentRegistLectureFragment : BaseFragment<FragmentContentRegistLectureBinding>(FragmentContentRegistLectureBinding::inflate), RegistDialog.OnItemClickListener {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

}