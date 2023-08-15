package com.example.jonggangtime.UI.Friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.SeekFriendData
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Lectures.SeekLecturesFragment
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentAlreadyListFiendsBinding
import com.example.jonggangtime.databinding.FragmentFriendsBinding


class AlreadyListFiendsFragment : BaseFragment<FragmentAlreadyListFiendsBinding>(FragmentAlreadyListFiendsBinding::inflate), FriendAdapter.OnItemClickListener {

    override fun initAfterBinding() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = arrayListOf<String>()
        for(i in 0..10){
            itemList.add("박지원")
        }

        val receiveAdapter = FriendAdapter(itemList, 0)
        receiveAdapter.setBottomSheetListener(this)
        binding.receiveListRv.adapter = receiveAdapter
        binding.receiveListRv.layoutManager = LinearLayoutManager(requireContext())

        val sendAdapter = FriendAdapter(itemList, 1)
        binding.sendListRv.adapter = sendAdapter
        binding.sendListRv.layoutManager = LinearLayoutManager(requireContext())

        val myFriendAdapter = FriendAdapter(itemList, 2)
        binding.myFriendsRv.adapter = myFriendAdapter
        binding.myFriendsRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClicked(option: Int) {
        when(option){
            0 -> {

            }
            1 ->{

            }
            else -> {

            }
        }
    }

}