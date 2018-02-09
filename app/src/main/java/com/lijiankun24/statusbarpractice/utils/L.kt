package com.lijiankun24.statusbarpractice.utils

import android.text.TextUtils
import android.util.Log

/**
 * L.java
 *
 *
 * Created by lijiankun on 17/6/5.
 */

object L {

    private var TAG = "lijk"

    fun initTAG(tag: String) {
        if (TextUtils.isEmpty(tag)) {
            return
        }
        TAG = tag
    }

    /**
     * log.d
     *
     * @param msg
     */
    fun d(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Log.d(TAG, msg)
    }

    /**
     * log.e
     *
     * @param msg
     */
    fun e(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Log.e(TAG, msg)
    }

    /**
     * log.e
     *
     * @param msg
     */
    fun e(msg: String, throwable: Throwable) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Log.e(TAG, msg, throwable)
    }

    /**
     * log.w
     *
     * @param msg
     */
    fun w(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Log.w(TAG, msg)
    }

    /**
     * Log.i
     *
     * @param msg
     */
    fun i(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Log.i(TAG, msg)
    }

    /**
     * log.v
     *
     * @param msg
     */
    fun v(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Log.v(TAG, msg)
    }
}