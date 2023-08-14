package com.example.jonggangtime.UI.Lectures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.databinding.ItemLectureBinding

class LectureAdapter(private val itemList: ArrayList<LectureData>): RecyclerView.Adapter<LectureAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLectureBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: LectureData) {
            binding.lectureNameTv.text = item.lectureName
            binding.lectureProfessorTv.text = item.professorName
            when(item.category){
//                TODO 정해진 카테고리의 숫자에 따라서 결정
            }
            binding.lectureCountTv.text = "수강 : ${item.nums}명"
            binding.lectureContentTv.text = item.shortContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemLectureBinding = ItemLectureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding

//        TODO onClickListener에 대한 것들
    }

    override fun getItemCount(): Int = itemList.size
}