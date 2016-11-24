package com.coderschool.booketplace.fragment;

import android.os.Bundle;

import com.coderschool.booketplace.api.FirebaseApi;
import com.google.firebase.database.Query;

/**
 * Created by dattran on 11/24/16.
 */

public class CategoryBookStreamFragment extends BookStreamFragment {
    private static final String CATEGORY = "category";

    public static CategoryBookStreamFragment newInstance(String category) {

        Bundle args = new Bundle();
        args.putString(CATEGORY, category);

        CategoryBookStreamFragment fragment = new CategoryBookStreamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Query getQuery() {
        String category = getArguments().getString(CATEGORY);
        return FirebaseApi.getInstance().getBookDatabaseRef().child(category.toLowerCase());
    }
}
