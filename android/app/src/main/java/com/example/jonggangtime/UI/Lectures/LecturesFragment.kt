package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentLecturesBinding

class LecturesFragment : BaseFragment<FragmentLecturesBinding>(FragmentLecturesBinding::inflate), MainActivity.onBackPressedListener {

    override fun initAfterBinding() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigation()
    }

    private fun initNavigation() {
        childFragmentManager.beginTransaction()
            .replace(R.id.lecture_fl, SeekLecturesFragment())
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        val fragmentList = childFragmentManager.fragments
        Log.d("frag", "lecturesFrag" + fragmentList.toString())
        for (fragment in fragmentList) {
            if (fragment is MainActivity.onBackPressedListener) {
                (fragment as MainActivity.onBackPressedListener).onBackPressed()
                return
            }
        }
    }
}