package com.example.jonggangtime.UI.Friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.UI.Friends.Retrofit.Friend
import com.example.jonggangtime.UI.Profile.Retrofit.CompletedLecture
import com.example.jonggangtime.databinding.ItemCompletedLectureBinding
import com.example.jonggangtime.databinding.ItemFriendBinding

class FriendsRVAdapter(private val friendList : ArrayList<Friend>) : RecyclerView.Adapter<FriendsRVAdapter.ViewHolder>() {

    private lateinit var mItemClickListener: OnItemClickListener

    // 클릭 리스너 구현 위한 인터페이스
    interface OnItemClickListener {
        fun onItemClick(data: Friend)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mItemClickListener = listener
    }


    inner class ViewHolder(val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(friend: Friend){
            binding.friendName.text = friend.friendName
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemFriendBinding =
            ItemFriendBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(friendList[position])
        }
    }

    override fun getItemCount(): Int {
        return friendList.size
    }
}