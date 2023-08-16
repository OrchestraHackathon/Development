package com.example.jonggangtime.UI.Lectures

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.DialogDayTimePickerBinding
import java.text.SimpleDateFormat

class PickerDialog: DialogFragment(), View.OnClickListener {

    lateinit var binding: DialogDayTimePickerBinding
    var state: Boolean = false
    val days = arrayOf("일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일")

    var day = 0
    val dataFormat = SimpleDateFormat("HH:mm") // 시(0~23) 분 초
    var startTime: String = dataFormat.format(System.currentTimeMillis())
    var endTime : String = startTime

    private var listener: OnPassData? = null

    interface OnPassData {
        fun onPassData(day: Int, startTime:String, endTime: String)
    }

    fun setOnPassDataListener(listener: OnPassData) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(TAG, "2 : PickerDialog 가 열림")
        binding = DialogDayTimePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()

        binding.noCl.setOnClickListener(this)
        binding.yesCl.setOnClickListener(this)

        binding.dayPickerNp.wrapSelectorWheel = false
        binding.dayPickerNp.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        binding.dayPickerNp.minValue = 0
        binding.dayPickerNp.maxValue = 6
        binding.dayPickerNp.displayedValues = days

        binding.startTimePickerTp.setIs24HourView(true)
        binding.endTimePickerTp.setIs24HourView(true)

        binding.dayPickerNp.setOnValueChangedListener { picker, oldVal, newVal ->
            Log.d(TAG, "3 : PickerDialog 날짜를 바꿈")
            day = newVal
        }

        binding.startTimePickerTp.setOnTimeChangedListener { view, hourOfDay, minute ->
            Log.d(TAG, "5 : PickerDialog 시간을 바꿈")
            if(hourOfDay<10){
                if(minute<10){
                    startTime = "0$hourOfDay:0$minute"
                } else{
                    startTime = "0$hourOfDay:$minute"
                }
            } else{
                if(minute<10){
                    startTime = "$hourOfDay:0$minute"
                } else{
                    startTime = "$hourOfDay:$minute"
                }
            }
        }

        binding.endTimePickerTp.setOnTimeChangedListener { view, hourOfDay, minute ->
            Log.d(TAG, "6 : PickerDialog 시간을 바꿈2")
            if(hourOfDay<10){
                if(minute<10){
                    endTime = "0$hourOfDay:0$minute"
                } else{
                    endTime = "0$hourOfDay:$minute"
                }
            } else{
                if(minute<10){
                    endTime = "$hourOfDay:0$minute"
                } else{
                    endTime = "$hourOfDay:$minute"
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.yes_cl -> {
                if(state){
//                    TODO 종료 데이터 전달
                    Log.d(TAG, "7 : PickerDialog 완료 버튼을 클릭함")
                    Log.d(TAG, "7 : PickerDialog 데이터를 전달\n" +
                            "$day, $startTime, $endTime")
                    listener!!.onPassData(day, startTime, endTime)
                    dismiss()
                } else {
                    Log.d(TAG, "4 : PickerDialog 다음 버튼을 클릭함")
//                    TODO 다음으로 이동
                    viewChange(state)
                    state = !state
                }
            }
            R.id.no_cl -> {
                if(state){
//                    TODO 이전으로 이동
                    viewChange(state)
                    state = !state
                } else {
//                    TODO 종료 -> 취소라서 데이터 전달 x
                    dismiss()
                }
            }
        }
    }

    fun viewChange(state: Boolean){
        if(state){
            binding.dayPickerNp.visibility = View.VISIBLE
            binding.startTimePickerTp.visibility = View.INVISIBLE
            binding.endTimePickerTp.visibility = View.INVISIBLE
            binding.noTv.text = "취소"
            binding.yesTv.text = "다음"
        } else{
            binding.dayPickerNp.visibility = View.INVISIBLE
            binding.startTimePickerTp.visibility = View.VISIBLE
            binding.endTimePickerTp.visibility = View.VISIBLE
            binding.noTv.text = "이전"
            binding.yesTv.text = "완료"
        }
    }

    fun initDialog(){
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}