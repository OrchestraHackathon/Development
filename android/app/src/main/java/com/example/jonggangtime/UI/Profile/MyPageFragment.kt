package com.example.jonggangtime.UI.Profile

import android.graphics.Paint
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate)  {

    override fun initAfterBinding() {
        //밑줄 표시
        //binding.myPageEmailTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }
}