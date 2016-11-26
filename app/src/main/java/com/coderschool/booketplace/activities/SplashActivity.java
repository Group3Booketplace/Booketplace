package com.coderschool.booketplace.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;

/**
 * Created by duongthoai on 10/13/16.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //
                if(FirebaseApi.getInstance().getUser() != null) {
                    startHome();
                } else {
                    startLogin();
                }
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {

    }

    private void startHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        //
        finish();
    }

    private void startLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        //
        finish();
    }
}
