package com.coderschool.booketplace;

import android.app.Application;

import com.coderschool.booketplace.api.FirebaseApi;

/**
 * Created by dattran on 11/29/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApi.getInstance();
    }
}
