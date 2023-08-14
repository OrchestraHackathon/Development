package com.example.jonggangtime.UI.Friends

import android.os.Bundle
import android.view.View
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Lectures.SeekLecturesFragment
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentFriendsBinding


class FriendsFragment : BaseFragment<FragmentFriendsBinding>(FragmentFriendsBinding::inflate) {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
        childFragmentManager.beginTransaction()
            .replace(R.id.friends_fl, ListFriendsFragment())
            .commitAllowingStateLoss()
    }
}