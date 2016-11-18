package com.coderschool.booketplace.fragment;

import android.os.Bundle;

import com.coderschool.booketplace.BaseFragmemt;

/**
 * Created by dattran on 11/18/16.
 */

public class UserProfileFragment extends BaseFragmemt {

    public static UserProfileFragment newInstance() {

        Bundle args = new Bundle();

        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
