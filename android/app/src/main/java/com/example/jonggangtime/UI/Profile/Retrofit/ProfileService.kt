package com.example.jonggangtime.UI.Profile.Retrofit

import android.util.Log
import com.example.jonggangtime.Utils.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class ProfileService {
    private lateinit var profileMyPageView: ProfileMyPageView

    fun setProfileMyPageView(profileMyPageView: ProfileMyPageView) {
        this.profileMyPageView = profileMyPageView
    }

    private val searchPartyService = NetworkModule.getInstance()?.create(ProfileRetrofitInterface::class.java)

    fun getMyPage(){
        searchPartyService?.mypage()?.enqueue(object : Callback<ResponseMyPage> {
            override fun onResponse(
                call: Call<ResponseMyPage>,
                response: Response<ResponseMyPage>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    val resp: ResponseMyPage = response.body()!!
                    profileMyPageView.profileMyPageSuccess(resp.result!!)
                }
            }

            override fun onFailure(call: Call<ResponseMyPage>, t: Throwable) {
                Log.d("profileService", "onFailure 호출됨")
            }

        })
    }
}