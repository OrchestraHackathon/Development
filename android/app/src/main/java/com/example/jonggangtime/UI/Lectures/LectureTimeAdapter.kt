package com.example.jonggangtime.UI.Lectures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.TimeData
import com.example.jonggangtime.databinding.ItemLectureTimeBinding

class LectureTimeAdapter: RecyclerView.Adapter<LectureTimeAdapter.ViewHolder>(){

    val itemList : ArrayList<TimeData> = arrayListOf()

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClicked(info: ArrayList<TimeData>)
    }

    fun setBottomSheetListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemLectureTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: TimeData){
            binding.dayTv.text = item.day
            binding.timeTv.text = item.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemLectureTimeBinding = ItemLectureTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding

        binding.deleteIv.setOnClickListener {
            itemList.remove(itemList[position])
            notifyDataSetChanged()
            listener!!.onItemClicked(itemList)
        }
    }

    fun setData(item: TimeData){
        itemList.add(item)
        notifyDataSetChanged()
    }
}