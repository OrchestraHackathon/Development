package com.example.jonggangtime.UI.Profile

import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.databinding.ActivityCertificateBinding

class CertificateActivity : BaseActivity<ActivityCertificateBinding>(ActivityCertificateBinding::inflate) {
    override fun initAfterBinding() {
        initClickListener()
    }

    private fun initClickListener() {
        binding.certificateBackBtn.setOnClickListener {
            finish()
        }
    }

}