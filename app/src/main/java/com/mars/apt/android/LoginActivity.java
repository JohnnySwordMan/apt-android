package com.mars.apt.android;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mars.apt.annotation.Builder;
import com.mars.apt.annotation.Inject;

/**
 * Created by JohnnySwordMan on 2021/12/31
 */
@Builder
public class LoginActivity extends AppCompatActivity {

    @Inject
    int username;
    @Inject
    int password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
