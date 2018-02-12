package com.lijiankun24.statusbarpractice.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.lijiankun24.statusbarpractice.R

class MainActivity : AppCompatActivity(), View.OnClickListener, Toolbar.OnMenuItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_dimming -> {
                startActivity(Intent(this, DimmingActivity::class.java))
            }
            R.id.btn_hiding_statusbar -> {
                startActivity(Intent(this, HideStatusbarActivity::class.java))
            }
            R.id.btn_hiding_navigation -> {
                startActivity(Intent(this, HideNavigationbarActivity::class.java))
            }
            R.id.btn_immersive -> {
                startActivity(Intent(this, ImmersiveActivity::class.java))
            }
            R.id.btn_responding -> {
                startActivity(Intent(this, RespondingActivity::class.java))
            }
            R.id.btn_transparent_statusbar -> {
                startActivity(Intent(this, TransparentStatusbarActivity::class.java))
            }
            R.id.btn_transparent_navigationbar -> {
                startActivity(Intent(this, TransparentNavigationbarActivity::class.java))
            }
            R.id.btn_fits_system_windows -> {
                startActivity(Intent(this, FitsSystemWindowsActivity::class.java))
            }
        }
    }

    private fun initView() {
        findViewById<View>(R.id.btn_dimming).setOnClickListener(this)
        findViewById<View>(R.id.btn_hiding_navigation).setOnClickListener(this)
        findViewById<View>(R.id.btn_hiding_statusbar).setOnClickListener(this)
        findViewById<View>(R.id.btn_immersive).setOnClickListener(this)
        findViewById<View>(R.id.btn_responding).setOnClickListener(this)
        findViewById<View>(R.id.btn_transparent_statusbar).setOnClickListener(this)
        findViewById<View>(R.id.btn_transparent_navigationbar).setOnClickListener(this)
        findViewById<View>(R.id.btn_fits_system_windows).setOnClickListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener(this)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setTitle(R.string.app_name)
        }
    }
}
