package com.lijiankun24.statusbarpractice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lijiankun24.statusbarpractice.R
import com.lijiankun24.statusbarpractice.utils.L

class RespondingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        respondingSystemUI()
        setContentView(R.layout.activity_responding)
        window.decorView.setOnSystemUiVisibilityChangeListener {
            L.i("visibility is " + it)
        }
    }

    private fun respondingSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE
    }
}
