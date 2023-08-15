package com.example.jonggangtime.UI.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.jonggangtime.Data.LoginInfo
import com.example.jonggangtime.Network.ResponseLogin
import com.example.jonggangtime.Network.RetrofitClient
import com.example.jonggangtime.UI.MainActivity
import com.example.jonggangtime.UI.Signin.SigninActivity
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginBtn.setOnClickListener {
            /*val loginInfo = LoginInfo(binding.emailInputTf.text.toString(), binding.passwordInputTf.text.toString())
            RetrofitClient.instance.login(loginInfo).enqueue(object: retrofit2.Callback<ResponseLogin>{
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "LoginActivity - Retrofit login() 실행결과 - 성공\n" +
                                    "body : ${response.body()}")

                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        Log.d(
                            TAG, "SigninActivity - Retrofit login() 실행결과 - 안좋음\n" +
                                    "body : ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Log.d(
                        TAG, "LoginActivity - Retrofit login() 실행결과 - 실패\n" +
                                "t : $t")
                }

            })*/

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        binding.signinTv.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }
    }
}