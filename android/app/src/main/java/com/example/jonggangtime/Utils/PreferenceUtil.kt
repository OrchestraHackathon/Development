package com.example.jonggangtime.Utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.jonggangtime.MyApplication.Companion.prefs

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun saveJwt(jwt: String){
        Log.d("jwt", jwt)
        val editor = prefs.edit()
        editor.putString("jwt", jwt)
        editor.apply()
    }
    fun getJwt(): String? = prefs.getString("jwt", null)

}