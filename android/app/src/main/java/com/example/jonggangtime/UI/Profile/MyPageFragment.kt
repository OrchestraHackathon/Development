package com.example.jonggangtime.UI.Profile

import android.util.Log
import com.bumptech.glide.Glide
import com.example.jonggangtime.UI.Profile.Retrofit.ProfileMyPageView
import com.example.jonggangtime.UI.Profile.Retrofit.ProfileService
import com.example.jonggangtime.UI.Profile.Retrofit.ResultMyPage
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate), ProfileMyPageView  {

    private lateinit var profileService: ProfileService

    override fun initAfterBinding() {
        //밑줄 표시
        //binding.myPageEmailTv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        Log.d("profile", "마이페이지 화면 띄워짐")
        profileService = ProfileService() // 서비스 객체 생성
        profileService.setProfileMyPageView(this)
        profileService.getMyPage()
    }

    override fun profileMyPageSuccess(result: ResultMyPage) {
        Glide.with(this)
            .load(result.profileImageUrl)
            .into(binding.myPageImgIv)
        binding.myPageNameTv.text = result.userName
        binding.myPageNicknameTv.text = result.userNickName
        binding.myPageEmailTv.text = result.email
        binding.myPageSentenceTv.text = result.aboutMe
    }

    override fun profileMyPageFailure(code: Int, message: String) {

    }
}