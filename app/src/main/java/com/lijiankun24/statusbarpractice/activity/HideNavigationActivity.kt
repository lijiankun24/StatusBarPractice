package com.lijiankun24.statusbarpractice.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lijiankun24.statusbarpractice.R

class HideNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavigationBar()
        setContentView(R.layout.activity_hide_navigation)
    }

    private fun hideNavigationBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}
