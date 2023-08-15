package com.example.jonggangtime.UI.Profile

import android.util.Log
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseFragment
import com.example.jonggangtime.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun initAfterBinding() {
        setFragment(R.id.profile_layout, MyPageFragment()) //초기에는 MyPage화면
        initClickListener()
    }

    private fun initClickListener() {

        binding.profileMyPageTv.setOnClickListener {
            Log.d("profile", "마이페이지 클릭함")
            binding.profileMyPageTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.profileMyPageTv.setTextSize(Dimension.SP, 20F)
            binding.profileCompletedClassesTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
            binding.profileCompletedClassesTv.setTextSize(Dimension.SP, 16F)
            setFragment(R.id.profile_layout, MyPageFragment())
        }

        binding.profileCompletedClassesTv.setOnClickListener {
            Log.d("profile", "수강 완료과목 클릭함")
            binding.profileMyPageTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
            binding.profileMyPageTv.setTextSize(Dimension.SP, 16F)
            binding.profileCompletedClassesTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.profileCompletedClassesTv.setTextSize(Dimension.SP, 20F)
            setFragment(R.id.profile_layout, CompletedLecturesFragment())
        }
    }
}