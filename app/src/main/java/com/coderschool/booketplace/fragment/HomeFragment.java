package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.coderschool.booketplace.BaseFragmemt;

/**
 * Created by duongthoai on 11/8/16.
 */

public class HomeFragment extends BaseFragmemt {
    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
