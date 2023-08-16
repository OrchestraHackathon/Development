package com.example.jonggangtime.UI.Signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.jonggangtime.Data.SigninInfo
import com.example.jonggangtime.Network.ResponseCreateDefaultTimeTable
import com.example.jonggangtime.Network.ResponseSignin
import com.example.jonggangtime.Network.RetrofitClient
import com.example.jonggangtime.R
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.Utils.TAG
import com.example.jonggangtime.databinding.ActivitySigninBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninActivity : BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {

    override fun initAfterBinding() {
        initTextWatcher()
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

                        RetrofitClient.instance.createDefaultTimeTable(response.body()!!.result.id.toLong()).enqueue(object : Callback<ResponseCreateDefaultTimeTable>{
                            override fun onResponse(
                                call: Call<ResponseCreateDefaultTimeTable>,
                                response: Response<ResponseCreateDefaultTimeTable>
                            ) {
                                if(response.isSuccessful){
                                    Log.d(TAG, "SigninActivity - Retrofit timetable 실행 결과 - 성공")
                                    finish()
                                } else{
                                    Log.d(TAG, "SigninActivity - Retrofit timetable 실행 결과 - 안좋음")
                                }
                            }

                            override fun onFailure(
                                call: Call<ResponseCreateDefaultTimeTable>,
                                t: Throwable
                            ) {
                                Log.d(TAG, "SigninActivity - Retrofit timetable 실행 결과 - 실패")
                            }
                        })

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

    private fun initTextWatcher(){
        binding.passwordCheckTf.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) {
                if (binding.passwordCheckTf.text?.length == 0){
                    binding.passwordCheckMsgTv.visibility = View.GONE
                }
                else if(binding.passwordInputTf.text.toString() != binding.passwordCheckTf.text.toString()){
                    binding.passwordCheckMsgTv.setTextColor(ContextCompat.getColor(this@SigninActivity, R.color.negative_red))
                    binding.passwordCheckMsgTv.text = "비밀번호를 다시 확인해주세요"
                    binding.passwordCheckMsgTv.visibility = View.VISIBLE // 비밀번호 밑에 안내창 보이게하기
                } else{
                    binding.passwordCheckMsgTv.setTextColor(ContextCompat.getColor(this@SigninActivity, R.color.blue_2))
                    binding.passwordCheckMsgTv.text = "비밀번호가 일치합니다"
                    binding.passwordCheckMsgTv.visibility = View.VISIBLE // 비밀번호 밑에 안내창 보이게하기
                }
            }

        })
    }
}