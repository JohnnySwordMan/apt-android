package com.mars.apt.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var mBtnLogin: Button

    lateinit var mBtnStartProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnLogin = findViewById(R.id.btn_login)
        mBtnStartProfile = findViewById(R.id.btn_start_profile)

        mBtnLogin.setOnClickListener {
            LoginActivityBuilder.start(this, "zhangsan01", "123-456")
        }

        mBtnStartProfile.setOnClickListener {
            ProfileActivityBuilder.start(this, "知春路记者", "123")
        }
    }

}