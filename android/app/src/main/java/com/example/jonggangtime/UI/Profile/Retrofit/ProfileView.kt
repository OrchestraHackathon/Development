package com.example.jonggangtime.UI.Profile.Retrofit

interface ProfileMyPageView {
    fun profileMyPageSuccess(result: ResultMyPage)
    fun profileMyPageFailure(code: Int, message: String)
}