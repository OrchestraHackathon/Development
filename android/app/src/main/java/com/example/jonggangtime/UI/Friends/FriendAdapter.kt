package com.example.jonggangtime.UI.Friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.databinding.ItemFriendBinding

class FriendAdapter(private val itemList: ArrayList<String>, val option: Int): RecyclerView.Adapter<FriendAdapter.ViewHolder>(){

//    option : 0 - 수락 대기중 요청, 1 - 내가 보낸 요청, 2 - 내 친구

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClicked(option: Int)
    }

    fun setBottomSheetListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemFriendBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String){
            binding.friendNameTv.visibility = View.GONE
            binding.friendNicknameTv.text = item
            when(option){
                1 -> {  //내가 보낸 요청
                    binding.checkIv.visibility = View.GONE
                }
                2 -> {  //내 친구
                    binding.cancelIv.visibility = View.GONE
                    binding.checkIv.visibility = View.GONE
                }
                else -> {  //수락 대기중 요청

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemFriendBinding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding

        binding.checkIv.setOnClickListener {
            if(option == 0){
//                TODO 친구 요청을 수락!
                listener!!.onItemClicked(option)
            }
        }
        binding.cancelIv.setOnClickListener {
            if(option == 0 || option == 1){
//                TODO 받거나 준 친구 요청을 삭제
                listener!!.onItemClicked(option)
            }
        }
        binding.friendItemCl.setOnClickListener {
            if(option == 2){
//                TODO 이전 화면에 알려서 DetailFriendFragment로 이동
                listener!!.onItemClicked(option)
            }
        }
    }
}