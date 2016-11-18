package com.coderschool.booketplace.api;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

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

    public interface FacebookApiResult {
        public void onSuccess(Object object);
        public void onFail();
    }
    private FacebookApi() {

    }

    public void getUserInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                // Pass data back
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "gender, location, birthday");
        request.setParameters(bundle);
        request.executeAsync();
    }
}
