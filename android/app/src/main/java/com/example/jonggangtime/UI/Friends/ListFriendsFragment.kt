/*
package com.example.jonggangtime.UI.Friends

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentDetailFriendBinding
import com.example.jonggangtime.databinding.FragmentListFriendsBinding
import com.google.android.material.textfield.TextInputEditText


class ListFriendsFragment : BaseFragment<FragmentListFriendsBinding>(FragmentListFriendsBinding::inflate) {

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigation()

        binding.searchTf.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                childFragmentManager.beginTransaction()
                    .replace(R.id.friend_list_fl, SeekListFriendsFragment())
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun initNavigation() {
        childFragmentManager.beginTransaction()
            .replace(R.id.friend_list_fl, AlreadyListFiendsFragment())
            .commitAllowingStateLoss()
    }
}*/
