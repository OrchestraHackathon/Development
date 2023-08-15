package com.example.jonggangtime.UI.Friends

import android.os.Bundle
import android.view.View
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentListFriendsBinding


class ListFriendsFragment : BaseFragment<FragmentListFriendsBinding>(FragmentListFriendsBinding::inflate) {

    override fun initAfterBinding() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigation()

        binding.friendsSearchEt.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                childFragmentManager.beginTransaction()
                    .addToBackStack("friend")
                    .replace(R.id.friend_list_fl, SeekListFriendsFragment())
                    .commitAllowingStateLoss()
            }
        }

        /*binding.searchTf.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                childFragmentManager.beginTransaction()
                    .replace(R.id.friend_list_fl, SeekListFriendsFragment())
                    .commitAllowingStateLoss()
            }
        }*/
    }

    private fun initNavigation() {
        childFragmentManager.beginTransaction()
            .replace(R.id.friend_list_fl, AlreadyListFiendsFragment())
            .commitAllowingStateLoss()
    }
}
