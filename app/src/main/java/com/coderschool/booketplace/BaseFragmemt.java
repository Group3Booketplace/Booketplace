package com.coderschool.booketplace;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by duongthoai on 11/8/16.
 */

public class BaseFragmemt extends Fragment {
    private AppCompatActivity appCompatActivity;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appCompatActivity = (AppCompatActivity) context;
    }
}
