package com.mars.apt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mars.apt.annotation.Builder

@Builder
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}