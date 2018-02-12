package com.lijiankun24.statusbarpractice.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lijiankun24.statusbarpractice.R

class DimmingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dimmingStatusBar()
        setContentView(R.layout.activity_dimming)
    }

    /**
     * 淡化状态栏和导航栏
     */
    private fun dimmingStatusBar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // This example uses decor view, but you can use any visible view.
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        }
    }

    private fun clearSystemUIFlag() {
        window.decorView.systemUiVisibility = 0
    }
}
