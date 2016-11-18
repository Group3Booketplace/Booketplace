package com.coderschool.booketplace.api;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static final String[] permission = new String[] {
        "user_about_me", "user_location", "user_friends", "email", "user_birthday"
    };

    public interface FacebookApiResult {
        public void onSuccess(Object object);
        public void onFail();
    }
    private FacebookApi() {

    }

    public void getUserInfo(AccessToken accessToken, FacebookApiResult result) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                // Pass data back
                Gson gson = new GsonBuilder().create();
                FacebookUser fbUser = gson.fromJson(object.toString(), FacebookUser.class);
                result.onSuccess(fbUser);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "gender, location, birthday");
        request.setParameters(bundle);
        request.executeAsync();
    }
}
