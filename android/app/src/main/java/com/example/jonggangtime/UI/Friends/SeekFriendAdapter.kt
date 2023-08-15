package com.example.jonggangtime.UI.Friends

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.SeekFriendData
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ItemSeekFriendBinding

class SeekFriendAdapter (private val itemList: ArrayList<SeekFriendData>): RecyclerView.Adapter<SeekFriendAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemSeekFriendBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SeekFriendData){
            binding.friendNicknameTv.text = item.nickname
            binding.friendNameTv.text = item.name
//            binding.checkIv.visibility = View.GONE
//            binding.cancelIv.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSeekFriendBinding = ItemSeekFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding

        binding.friendItemCl.setOnClickListener {
//            TODO 이전 화면에 알려서 서버에 친구 추가 요청을 하기!
            Log.d(TAG, "SeekFriendAdapter - itme clicked\n" +
                    "item : ${itemList[position]}")
        }
    }
}
