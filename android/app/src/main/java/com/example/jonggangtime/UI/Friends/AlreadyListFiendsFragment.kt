package com.example.jonggangtime.UI.Friends

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Friends.Retrofit.Friend
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentAlreadyListFiendsBinding


class AlreadyListFiendsFragment : BaseFragment<FragmentAlreadyListFiendsBinding>(FragmentAlreadyListFiendsBinding::inflate)/*, FriendAdapter.OnItemClickListener */{

    private lateinit var friendsRVAdapter: FriendsRVAdapter
    private lateinit var friendRecievedRVAdapter: FriendsRequestRVAdapter
    private lateinit var friendSendRVAdapter: FriendsRequestRVAdapter

    private var friendsArray = java.util.ArrayList<Friend>()
    private var friendsRecievedArray = java.util.ArrayList<Friend>()
    private var friendsSendArray = java.util.ArrayList<Friend>()

    override fun initAfterBinding() {
        initAdapter()
        initDummy()
    }

    private fun initDummy() {
        friendsArray.apply {
            add(Friend(1, "이준영"))
            add(Friend(2, "박지원"))
            add(Friend(3, "이주언"))
            add(Friend(4, "남보우"))
            add(Friend(5, "정재연"))
        }
        friendsRecievedArray.apply {
            add(Friend(1, "이준영"))
            add(Friend(2, "박지원"))
        }
        friendsSendArray.apply {
            add(Friend(3, "이주언"))
            add(Friend(4, "남보우"))
            add(Friend(5, "정재연"))
        }
    }

    private fun initAdapter() {
        // 내 친구
        friendsRVAdapter = FriendsRVAdapter(friendsArray)
        binding.friendsMyFriendRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        friendsRVAdapter.setOnItemClickListener(object : FriendsRVAdapter.OnItemClickListener{
            override fun onItemClick(data: Friend) {
                //TODO: 클릭했을 때 무슨 행동을 할지
                Log.d("timetable", data.friendName + "클릭됨")
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack("friends").replace(R.id.main_fl,FriendTimeTableFragment()).commit()
            }
        })
        binding.friendsMyFriendRv.adapter = friendsRVAdapter

        // 수락 대기 중인 요청
        friendRecievedRVAdapter = FriendsRequestRVAdapter(friendsRecievedArray, 1)
        binding.friendsRecievedRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        friendRecievedRVAdapter.setOnItemClickListener(object : FriendsRequestRVAdapter.OnItemClickListener{
            override fun onItemClick(data: Friend) {

            }

        })
        binding.friendsRecievedRv.adapter = friendRecievedRVAdapter

        // 내가 보낸 요청
        friendSendRVAdapter = FriendsRequestRVAdapter(friendsSendArray, 2)
        binding.friendsSendRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        friendSendRVAdapter.setOnItemClickListener(object : FriendsRequestRVAdapter.OnItemClickListener{
            override fun onItemClick(data: Friend) {

            }
        })
        binding.friendsSendRv.adapter = friendSendRVAdapter

    }

    /*override fun initAfterBinding() {

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
    }*/

}
