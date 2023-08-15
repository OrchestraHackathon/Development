package com.example.jonggangtime.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BaseURL: String = "https://413a-121-130-156-219.ngrok-free.app/"

object RetrofitClient{
    val retrofit = getRetrofit()
    val instance = retrofit.create(RetrofitInterface::class.java)
}

fun okHttpClient() : OkHttpClient {
    val builder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return builder.addInterceptor(logging).build()
}

fun getRetrofit(): Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl(BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient())
        .build()

    return retrofit
}