package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.jonggangtime.databinding.DialogBottomSheetDetailLectureBinding

class DetailLectureDialog: DialogFragment(){
    lateinit var binding: DialogBottomSheetDetailLectureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogBottomSheetDetailLectureBinding.inflate(inflater, container, false)
        return binding.root
    }
}