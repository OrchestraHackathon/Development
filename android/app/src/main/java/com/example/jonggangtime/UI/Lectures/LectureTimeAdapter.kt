package com.example.jonggangtime.UI.Lectures

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ItemLectureTimeBinding
import com.islandparadise14.mintable.model.ScheduleEntity

class LectureTimeAdapter: RecyclerView.Adapter<LectureTimeAdapter.ViewHolder>(){

    val days = arrayOf("일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일")

    val itemList : ArrayList<ScheduleEntity> = arrayListOf()

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClicked(info: ArrayList<ScheduleEntity>)
    }

    fun setBottomSheetListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemLectureTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ScheduleEntity){
            binding.dayTv.text = days[item.scheduleDay]
            binding.timeTv.text = "${item.startTime}~${item.endTime}"
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
            Log.d(TAG, "14 : LectureTimeAdapter 원래 데이터\n" +
                    "itemList : ${itemList.size}" )
            Log.d(TAG, "15 : LectureTimeAdapter 데이터를 삭제함\n" +
                    "data : ${itemList[position]} " )
            itemList.remove(itemList[position])
            Log.d(TAG, "16 : LectureTimeAdapter 바뀐 데이터\n" +
                    "itemList : ${itemList.size}" )
            notifyDataSetChanged()
            listener!!.onItemClicked(itemList)
        }
    }

    fun setData(item: ScheduleEntity){
        Log.d(TAG, "10 : LectureTimeAdapter 원래 데이터\n" +
                "itemList : ${itemList.size}" )
        Log.d(TAG, "11 : LectureTimeAdapter 데이터를 전달받음\n" +
                "data : $item " )
        itemList.add(item)
        Log.d(TAG, "12 : LectureTimeAdapter 바뀐 데이터\n" +
                "itemList : ${itemList.size}" )
        notifyDataSetChanged()
    }
}