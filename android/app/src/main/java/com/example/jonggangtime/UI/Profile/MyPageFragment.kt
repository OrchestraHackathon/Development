package com.example.jonggangtime.UI.Profile

import android.graphics.Paint
import android.util.Log
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate)  {

    override fun initAfterBinding() {
        //밑줄 표시
        //binding.myPageEmailTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        Log.d("profile", "마이페이지 화면 띄워짐")
    }
}