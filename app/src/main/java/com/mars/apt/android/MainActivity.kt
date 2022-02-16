package com.mars.apt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mars.apt.annotation.Builder
import com.mars.apt.annotation.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mBtnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnLogin = findViewById(R.id.btn_login)

        mBtnLogin.setOnClickListener {
            LoginActivityBuilder.start(this, "zhangsan", "123")
        }
    }
}