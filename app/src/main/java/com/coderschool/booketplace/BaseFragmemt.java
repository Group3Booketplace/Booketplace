package com.coderschool.booketplace;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by duongthoai on 11/8/16.
 */

public class BaseFragmemt extends Fragment {
    private BaseActivity appCompatActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        appCompatActivity = (BaseActivity) context;
    }


    protected BaseActivity getBaseActivity() {
        return appCompatActivity;
    }

}
