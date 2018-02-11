package com.lijiankun24.statusbarpractice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lijiankun24.statusbarpractice.R

class DimmingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dimming)
        dimmingStatusBar()
    }

    /**
     * 显示状态栏和导航栏
     */
    private fun dimmingStatusBar() {
        // This example uses decor view, but you can use any visible view.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
    }
}
