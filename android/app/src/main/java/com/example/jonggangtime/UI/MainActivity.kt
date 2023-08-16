package com.example.jonggangtime.UI

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Friends.FriendsFragment
import com.example.jonggangtime.UI.Lectures.LecturesFragment
import com.example.jonggangtime.UI.Profile.ProfileFragment
import com.example.jonggangtime.UI.TimeTable.TimeTableFragment
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private var backPressedTime: Long = 0

    override fun initAfterBinding() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fl, TimeTableFragment())
            .commitAllowingStateLoss()


        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.timeTable_menu -> {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_fl, TimeTableFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.lecture_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, LecturesFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.friend_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, FriendsFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.my_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fl, ProfileFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed() {

        Log.d("Stack-Log", "onBackPressed() - Activity")
        val fragmentList = supportFragmentManager.fragments
        Log.d("frag", "onBackPressed()" + fragmentList.toString())
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                Log.d("frag", "return됨")
                return
            }
        }

          // 2초안에 뒤로가기 2번 누르면 종료
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }



}