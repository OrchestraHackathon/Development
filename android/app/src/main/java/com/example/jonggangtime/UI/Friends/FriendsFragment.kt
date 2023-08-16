package com.example.jonggangtime.UI.Friends

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentFriendsBinding


class FriendsFragment : BaseFragment<FragmentFriendsBinding>(FragmentFriendsBinding::inflate), MainActivity.onBackPressedListener {

    override fun initAfterBinding() {

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

    override fun onBackPressed() {
        val fragmentList = childFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is MainActivity.onBackPressedListener) {
                (fragment as MainActivity.onBackPressedListener).onBackPressed()
                return
            }
        }
    }
}