package com.mars.apt.android

import android.app.Application
import com.mars.apt.runtime.ActivityBuilder

/**
 * Created by JohnnySwordMan on 2/17/22
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityBuilder.init(this)
    }
}