package com.lijiankun24.statusbarpractice.utils

import android.content.Context

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Utils.java
 *
 *
 * Created by lijiankun on 18/1/31.
 */

object Utils {


    /**
     * 判断是否存在 NavigationBar
     *
     * @return true 存在; false 不存在
     */
    fun checkDeviceHasNavigationBar(context: Context): Boolean {
        var hasNavigationBar = false
        val id = context.resources.getIdentifier("config_showNavigationBar", "bool", "android")
        if (id > 0) {
            hasNavigationBar = context.resources.getBoolean(id)
        }

        try {
            val systemPropertiesClass = Class.forName("android.os.SystemProperties")
            val m = systemPropertiesClass.getMethod("get", String::class.java)
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
            if ("1" == navBarOverride) {
                hasNavigationBar = false
            } else if ("0" == navBarOverride) {
                hasNavigationBar = true
            }
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }

        return hasNavigationBar
    }

}
