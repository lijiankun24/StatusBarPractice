package com.lijiankun24.statusbarpractice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.lijiankun24.statusbarpractice.R

class HideNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hide_navigation)
    }

    /**
     * 隐藏状态栏和导航栏
     */
    private fun hideStatusBarAndNavigationBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION           // hide nav bar

                or View.SYSTEM_UI_FLAG_FULLSCREEN)               // hide status bar
    }

    /**
     * 显示状态栏和导航栏
     */
    private fun showStatusBarAndNavigationBar() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    private fun setFullScreen(enable: Boolean) {
        if (enable) {
            val attrs = window.attributes
            attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            window.attributes = attrs
        } else {
            val attrs = window.attributes
            attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
            window.attributes = attrs
        }
    }
}
