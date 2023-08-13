package com.example.jonggangtime.UI

import android.os.Bundle
import com.example.jonggangtime.R
import com.example.jonggangtime.UI.Friends.FriendsFragment
import com.example.jonggangtime.UI.Lectures.LecturesFragment
import com.example.jonggangtime.UI.Profile.ProfileFragment
import com.example.jonggangtime.UI.TimeTable.TimeTableFragment
import com.example.jonggangtime.Utils.BaseActivity
import com.example.jonggangtime.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

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

}