package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Friends.FriendsFragment
import com.example.jonggangtime.UI.My.MyFragment
import com.example.jonggangtime.UI.TimeTable.TimeTableFragment
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentLecturesBinding

class LecturesFragment : BaseFragment<FragmentLecturesBinding>(FragmentLecturesBinding::inflate) {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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