package com.example.jonggangtime.UI.Friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.SeekFriendData
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentListFriendsBinding
import com.example.jonggangtime.databinding.FragmentSeekListFriendsBinding


class SeekListFriendsFragment : BaseFragment<FragmentSeekListFriendsBinding>(FragmentSeekListFriendsBinding::inflate) {

    override fun initAfterBinding() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = arrayListOf<SeekFriendData>()
        for(i in 0..10){
            itemList.add(SeekFriendData("지원지원", "박지원"))
        }

        val seekFriendAdapter = SeekFriendAdapter(itemList)
        binding.friendsListRv.adapter = seekFriendAdapter
        binding.friendsListRv.layoutManager = LinearLayoutManager(requireContext())
    }

}