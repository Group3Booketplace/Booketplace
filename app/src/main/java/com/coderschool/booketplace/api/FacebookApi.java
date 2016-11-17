package com.coderschool.booketplace.api;

/**
 * Created by dattran on 11/17/16.
 */

public class FacebookApi {
    private static final String TAG = FacebookApi.class.getSimpleName();

    // singleton firebase instance
    private static volatile FacebookApi sInstance;

    public static FacebookApi getInstance() {
        if (sInstance == null) {
            synchronized (TAG) {
                if (sInstance == null) {
                    sInstance = new FacebookApi();
                }
            }
        }
        return sInstance;
    }

    private FacebookApi() {

    }
}
