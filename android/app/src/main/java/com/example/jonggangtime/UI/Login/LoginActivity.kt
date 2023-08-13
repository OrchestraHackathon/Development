package com.example.jonggangtime.UI.Login

import android.content.Intent
import android.os.Bundle
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.UI.Signin.SigninActivity
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.signinTv.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }
    }
}