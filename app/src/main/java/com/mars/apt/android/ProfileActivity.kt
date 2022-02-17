package com.mars.apt.android

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.mars.apt.annotation.Builder
import com.mars.apt.annotation.Inject

/**
 * Created by JohnnySwordMan on 2/16/22
 */
@Builder
class ProfileActivity: Activity() {

    @Inject
    lateinit var profileName: String

    @Inject
    lateinit var profileId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val mTvNickName = findViewById<TextView>(R.id.tv_nickname)
        val mTvUserId = findViewById<TextView>(R.id.tv_user_id)

        mTvNickName.text = profileName
        mTvUserId.text = profileId
    }
}