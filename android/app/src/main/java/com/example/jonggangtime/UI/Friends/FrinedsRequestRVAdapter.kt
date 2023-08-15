package com.example.jonggangtime.UI.Friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.UI.Friends.Retrofit.Friend
import com.example.jonggangtime.databinding.ItemFriendBinding
import com.example.jonggangtime.databinding.ItemFriendRequestBinding

class FriendsRequestRVAdapter(private val requestList : ArrayList<Friend>, val type: Int) : RecyclerView.Adapter<FriendsRequestRVAdapter.ViewHolder>() {
    private lateinit var mItemClickListener: OnItemClickListener

    // 클릭 리스너 구현 위한 인터페이스
    interface OnItemClickListener {
        fun onItemClick(data: Friend)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mItemClickListener = listener
    }


    inner class ViewHolder(val binding: ItemFriendRequestBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(request: Friend){
            binding.friendName.text = request.friendName
            if (type == 2){ // 내가 보낸 요청이면
                binding.friendRequestCheckIv.visibility = View.GONE // 체크 표시 없애기
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemFriendRequestBinding =
            ItemFriendRequestBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(requestList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(requestList[position])
        }
    }

    override fun getItemCount(): Int {
        return requestList.size
    }


}