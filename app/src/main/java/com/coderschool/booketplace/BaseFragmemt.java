package com.coderschool.booketplace;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.coderschool.booketplace.fragment.DetailFragment;

/**
 * Created by duongthoai on 11/8/16.
 */

public class BaseFragmemt extends Fragment {
    protected BaseActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }


    protected BaseActivity getBaseActivity() {
        return mActivity;
    }

    protected void replaceFragment(Fragment fragment) {
        if(getBaseActivity() != null) {
            getBaseActivity().replaceFragment(R.id.frame, fragment, true);
        }
    }
}
