package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.jonggangtime.databinding.DialogRegistBinding

class RegistDialog: DialogFragment() {

    lateinit var binding: DialogRegistBinding

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClicked(result :Boolean)
    }

    fun setBottomSheetListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogRegistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yesCl.setOnClickListener {
//            TODO 여기를 클릭하면 시간을 선택하는 화면으로 넘어가기!
            listener!!.onItemClicked(true)
            dismiss()
        }
        binding.noCl.setOnClickListener {
            listener!!.onItemClicked(false)
            dismiss()
        }
    }
}