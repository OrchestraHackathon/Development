package com.example.jonggangtime.Utils

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(private val inflate: (LayoutInflater) -> T) :
    AppCompatActivity() {
    protected lateinit var binding: T
        private set

    private var imm: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

        initAfterBinding()
    }

    protected abstract fun initAfterBinding()

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun startActivityWithClear(activity: Class<*>?) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    // 키보드 숨기기
    fun hideKeyboard(v: View) {
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun changeActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    // 액티비티에서 플래그먼트 설정하는 함수
    fun setFragment(id: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, fragment).commit()
    }
}