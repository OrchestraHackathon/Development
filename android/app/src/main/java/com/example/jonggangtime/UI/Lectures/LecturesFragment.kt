package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.view.View
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentLecturesBinding

class LecturesFragment : BaseFragment<FragmentLecturesBinding>(FragmentLecturesBinding::inflate) {

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
}