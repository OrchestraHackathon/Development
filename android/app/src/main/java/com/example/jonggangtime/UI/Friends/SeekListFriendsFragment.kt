package com.example.jonggangtime.UI.Friends

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonggangtime.Data.SeekFriendData
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Lectures.RegistDialog
import com.example.jonggangtime.UI.Lectures.SeekLecturesFragment
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentSeekListFriendsBinding


class SeekListFriendsFragment : BaseFragment<FragmentSeekListFriendsBinding>(FragmentSeekListFriendsBinding::inflate), SeekFriendAdapter.OnItemClickListener, RegistDialog.OnAnswerClickListener {

    override fun initAfterBinding() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = arrayListOf<SeekFriendData>()
        for(i in 0..10){
            itemList.add(SeekFriendData("지원지원", "박지원"))
        }

        val seekFriendAdapter = SeekFriendAdapter(itemList)
        seekFriendAdapter.setBottomSheetListener(this)
        binding.friendsListRv.adapter = seekFriendAdapter
        binding.friendsListRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClicked(info: SeekFriendData) {
        val dialog = RegistDialog(1)
        dialog.setBottomSheetListener(this)
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "registDialog")
    }

    override fun onAnswerClicked(result: Boolean) {
        if(result){
//            TODO 서버에 전달 -> 서버 전달 완료한 이후에 화면 전환
            parentFragmentManager.beginTransaction()
                .replace(R.id.friend_list_fl, AlreadyListFiendsFragment())
                .commitAllowingStateLoss()
        } else {
//            다시 받아야 하나?
        }
    }

}
