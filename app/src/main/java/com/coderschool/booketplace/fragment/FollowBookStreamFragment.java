package com.coderschool.booketplace.fragment;

import android.os.Bundle;

import com.coderschool.booketplace.api.FirebaseApi;
import com.google.firebase.database.Query;

/**
 * Created by dattran on 11/28/16.
 */

public class FollowBookStreamFragment extends BookStreamFragment {
    private static final String UID = "uid";

    public static FollowBookStreamFragment newInstance(String uid) {

        Bundle args = new Bundle();
        args.putString(UID, uid);

        FollowBookStreamFragment fragment = new FollowBookStreamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Query getQuery() {
        return FirebaseApi.getInstance().getUserBookDatabaseRef();
    }
}
