package com.example.jonggangtime.UI.Signin

import android.os.Bundle
import android.util.Log
import com.example.jonggangtime.Data.SigninInfo
import com.example.jonggangtime.Network.ResponseSignin
import com.example.jonggangtime.Network.RetrofitClient
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ActivitySigninBinding
import retrofit2.Call
import retrofit2.Response

class SigninActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.completeBtn.setOnClickListener {
            val signinInfo = SigninInfo(binding.emailInputTf.text.toString(), binding.passwordInputTf.text.toString(), binding.nameInputTf.text.toString(), binding.nicknameInputTf.text.toString())
            RetrofitClient.instance.signin(signinInfo).enqueue(object: retrofit2.Callback<ResponseSignin>{
                override fun onResponse(
                    call: Call<ResponseSignin>,
                    response: Response<ResponseSignin>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "SigninActivity - Retrofit signin() 실행결과 - 성공\n" +
                                "body : ${response.body()}")

                        finish()
                    } else {
                        Log.d(TAG, "SigninActivity - Retrofit signin() 실행결과 - 안좋음\n" +
                                "body : ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<ResponseSignin>, t: Throwable) {
                    Log.d(TAG, "SigninActivity - Retrofit signin() 실행결과 - 실패\n" +
                            "t : $t")
                }

            })
        }

        /*binding.passwordCheckTf.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if(checkPassword()){

                } else {

                }
            }

        })*/
    }

    /*fun checkPassword(): Boolean{
        return binding.passwordInputTf.text.toString() == binding.passwordCheckTf.text.toString()
    }
    fun checkPermission(){
        if()
    }*/
}