package com.mars.apt.runtime

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

/**
 * Created by JohnnySwordMan on 2/16/22
 */
object ActivityBuilder {

    private var mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.e("gy", "onActivityCreated")
            // 反射调用LoginActivityBuilder中的inject方法

            // 测试代码---start
            val cls = Class.forName("com.mars.apt.android.LoginActivityBuilder")
            cls.getDeclaredMethod("inject").invoke(null)
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
            Log.e("gy", "onActivityResumed")
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }

    }

    fun init(context: Context) {
        val application = context.applicationContext as Application
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    fun startActivity(context: Context, intent: Intent) {
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}