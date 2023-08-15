package com.example.jonggangtime.UI.Profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.jonggangtime.databinding.DialogScoreEvaluationBinding

class DialogScoreEvaluation : DialogFragment() {
    lateinit var binding: DialogScoreEvaluationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogScoreEvaluationBinding.inflate(inflater, container, false)
        initViewSetting()
        initNumPicker()
        initClickListener()
        return binding.root
    }

    private fun initNumPicker() {
        var scoreArray = arrayOf("A+", "A", "B+", "B", "C+", "C", "F")
        binding.scoreEvaluationPicker.minValue = 0
        binding.scoreEvaluationPicker.maxValue = scoreArray.size-1
        binding.scoreEvaluationPicker.displayedValues = scoreArray
    }

    private fun initClickListener() {
        binding.scoreEvaluationCancelBtn.setOnClickListener {
            dismiss()
        }

        binding.scoreEvaluationOkTv.setOnClickListener {
            //TODO: 성적 평가 API 연결
        }
    }

    private fun initViewSetting() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 투명하게 만들어줘야 둥근 테두리가 보인다.
        //다이얼로그! 동적인 화면 크기 구성
        val params = dialog!!.window!!.attributes
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

}