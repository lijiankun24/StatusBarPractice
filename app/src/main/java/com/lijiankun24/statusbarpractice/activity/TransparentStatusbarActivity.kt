package com.lijiankun24.statusbarpractice.activity

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lijiankun24.statusbarpractice.R

class TransparentStatusbarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentingStatusbar()
        setContentView(R.layout.activity_transparent_statusbar)
    }

    private fun transparentingStatusbar() {
        var uiFlag = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or uiFlag
        }
        window.decorView.systemUiVisibility = uiFlag
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}
