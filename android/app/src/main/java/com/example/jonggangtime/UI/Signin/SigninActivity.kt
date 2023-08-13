package com.example.jonggangtime.UI.Signin

import android.os.Bundle
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.databinding.ActivitySigninBinding

class SigninActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.completeBtn.setOnClickListener {
            finish()
        }
    }

}