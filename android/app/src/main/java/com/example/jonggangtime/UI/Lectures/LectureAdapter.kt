package com.example.jonggangtime.UI.Lectures

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.LectureData
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ItemLectureBinding
import com.example.jonggangtime.databinding.ItemLoadingBinding

class LectureAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
/*class LectureAdapter(private val itemList: ArrayList<LectureData>): RecyclerView.Adapter<LectureAdapter.ViewHolder>(){*/

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClicked(info: LectureData)
    }

    fun setBottomSheetListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private val items = ArrayList<LectureData>()

    // 아이템뷰에 게시물이 들어가는 경우
    inner class NoticeViewHolder(val binding: ItemLectureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LectureData) {
            binding.lectureTitleTv.text = item.lectureName
            binding.lectureProfessorTv.text = item.professorName
            when (item.category) {
//                TODO 정해진 카테고리의 숫자에 따라서 결정
            }
            binding.lectureCountTv.text = "수강 : ${item.nums}명"
            binding.lectureContentTv.text = item.shortContent
        }
    }

    // 아이템뷰에 프로그레스바가 들어가는 경우
    inner class LoadingViewHolder(val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
        // 게시물과 프로그레스바 아이템뷰를 구분할 기준이 필요하다.
        return when (items[position].lectureName) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLectureBinding.inflate(layoutInflater, parent, false)
                NoticeViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NoticeViewHolder) { //내용
            holder.bind(items[position])
            val binding = holder.binding

            binding.lectureItemCv.setOnClickListener {
                listener!!.onItemClicked(items[position])
            }
        } else { //progressbar
        }
    }

    fun setList(itemList: ArrayList<LectureData>) {
        Log.d(TAG, "LectureAdapter - setList() 실행\n" +
                "itemList : $itemList\n" +
                "items : $items")
        items.addAll(itemList)
        items.add(LectureData(" ", " ", 0, 0, "")) // progress bar 넣을 자리
        this.notifyDataSetChanged()
    }

    fun deleteLoading() {
        Log.d(TAG, "LectureAdapter - deleteLoading() 실행")
        items.removeAt(items.lastIndex) // 로딩이 완료되면 프로그레스바를 지움
    }

    /*inner class ViewHolder(val binding: ItemLectureBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: LectureData) {
            binding.lectureTitleTv.text = item.lectureName
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
        binding.lectureItemCv.setOnClickListener {
            listener!!.onItemClicked(itemList[position])
        }
    }

    override fun getItemCount(): Int = itemList.size*/
}
