package com.coderschool.booketplace.fragment;

import android.os.Bundle;

import com.coderschool.booketplace.api.FirebaseApi;
import com.google.firebase.database.Query;

/**
 * Created by dattran on 11/24/16.
 */

public class UserBookStreamFragment extends BookStreamFragment {
    private static final String USER = "user";

    public static UserBookStreamFragment newInstance(String uid) {

        Bundle args = new Bundle();
        args.putString(USER, uid);

        UserBookStreamFragment fragment = new UserBookStreamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Query getQuery() {
        String uid = getArguments().getString(USER);
        return FirebaseApi.getInstance().getUserBookDatabaseRef().child(uid);
    }
}
