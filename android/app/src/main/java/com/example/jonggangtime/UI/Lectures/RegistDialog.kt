package com.example.jonggangtime.UI.Lectures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.jonggangtime.R
import com.example.jonggangtime.databinding.DialogRegistBinding

class RegistDialog(val option: Int): DialogFragment() {

//    option : 0 - ContentRegistLectrueFragment(과목을 등록할 때), 1 - SeekListFriendsFragment(친구를 추가할때)

    lateinit var binding: DialogRegistBinding

    private var listener: OnAnswerClickListener? = null

    interface OnAnswerClickListener {
        fun onAnswerClicked(result :Boolean)
    }

    fun setBottomSheetListener(listener: OnAnswerClickListener) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle( // Background -> Transparent.
            STYLE_NORMAL,
            R.style.TransparentBottomSheetDialogFragment
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(option){
            1 -> {
                binding.content.text = "친구 요청을 보내시겠습니까?"
            }
            else -> {
                binding.content.text = "내 시간표에 바로 추가 하시겠습니까?"
            }
        }

        binding.yesCl.setOnClickListener {
//            TODO 여기를 클릭하면 시간을 선택하는 화면으로 넘어가기!
            listener!!.onAnswerClicked(true)
            dismiss()
        }
        binding.noCl.setOnClickListener {
            listener!!.onAnswerClicked(false)
            dismiss()
        }
    }
}