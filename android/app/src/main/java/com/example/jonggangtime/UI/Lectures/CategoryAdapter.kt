package com.example.jonggangtime.UI.Lectures

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jonggangtime.Data.LectureCategoryData
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ItemCategoryBinding

//TODO 색상변경이 안됨

class CategoryAdapter(val context: Context, private val itemList: ArrayList<LectureCategoryData>, private val option: Int): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

//    opton -> 0 - DetailLectureFragment, 1 - ContentRegistLectureFragment
    private val categoryState: ArrayList<Boolean> = arrayListOf(false, false, false, false, false, false)
    private val categoryColor = arrayListOf(R.color.category_study, R.color.category_certificate, R.color.category_routine, R.color.category_exercies, R.color.category_parttime, R.color.category_hobby)


    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClicked(info: ArrayList<Boolean>)
    }

    fun setBottomSheetListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LectureCategoryData) {
            binding.categoryNameTv.text = item.categoryName
            when (option) {
                1 -> {
                    binding.lectureCategoryCv.setCardBackgroundColor(ContextCompat.getColor(context, R.color.category_unactive))
                }
                0 -> {
                    binding.lectureCategoryCv.setCardBackgroundColor(ContextCompat.getColor(context, categoryColor[item.categoryId]))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCategoryBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding

        if(option == 1){
            binding.lectureCategoryCv.setOnClickListener {
                if(categoryState[position]){
                    binding.lectureCategoryCv.setCardBackgroundColor(ContextCompat.getColor(context, R.color.category_unactive))
                    binding.lectureCategoryCv
                    categoryState[position] = false
                } else {
                    binding.lectureCategoryCv.setCardBackgroundColor(ContextCompat.getColor(context, categoryColor[position]))
                    binding.lectureCategoryCv
                    categoryState[position] = true
                }

                Log.d(TAG, "CategoryAdapter - category 정보 변경\n" +
                        "categoryState : $categoryState")
                listener!!.onItemClicked(categoryState)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}