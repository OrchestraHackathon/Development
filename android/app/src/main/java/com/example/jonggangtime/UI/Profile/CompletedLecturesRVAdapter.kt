package com.example.jonggangtime.UI.Profile

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Profile.Retrofit.CompletedLecture
import com.example.jonggangtime.databinding.ItemCompletedLectureBinding

class CompletedLecturesRVAdapter(private val completedLectureList : ArrayList<CompletedLecture>) : RecyclerView.Adapter<CompletedLecturesRVAdapter.ViewHolder>() {

    private lateinit var mItemClickListener: OnItemClickListener

    // 클릭 리스너 구현 위한 인터페이스
    interface OnItemClickListener {
        fun onItemClick(data: CompletedLecture)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mItemClickListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CompletedLecturesRVAdapter.ViewHolder {
        val binding : ItemCompletedLectureBinding =
            ItemCompletedLectureBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(completedLectureList[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(completedLectureList[position])
        }
    }

    override fun getItemCount(): Int {
        return completedLectureList.size
    }

    inner class ViewHolder(val binding: ItemCompletedLectureBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(completedLecture : CompletedLecture){
            binding.completedLectureTitleTv.text = completedLecture.courseName
            binding.completedLectureProfessorTv.text = completedLecture.professor
            binding.completedLectureContentTv.text = completedLecture.courseDetails
            binding.completedLectureCategoryTv.text = completedLecture.categoryName
            binding.completedLectureCountTv.text = "수강: " + completedLecture.registerPeople
            //TODO: 성적평가를 한 과목인지 아닌 지 여부에 따라 성적 평가/ 수료증 보기 띄워주기
            if (completedLecture.flag) { // 성적평가를 한 과목이라면
                Log.d("completed", "성적평가 완료함")
                binding.completedLectureScoreTv.text = "수료증 보기"
                binding.completedLectureScoreCv.backgroundTintList = ColorStateList.valueOf(binding.root.context.getColor(R.color.gray_0))
            }
        }
    }
}