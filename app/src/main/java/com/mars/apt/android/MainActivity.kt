package com.mars.apt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mars.apt.annotation.Builder
import com.mars.apt.annotation.Inject

@Builder
class MainActivity : AppCompatActivity() {

    @Inject
    var name:String? = null
    @Inject
    var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}