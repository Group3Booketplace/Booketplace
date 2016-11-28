package com.coderschool.booketplace;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;

import com.facebook.FacebookSdk;

/**
 * Created by duongthoai on 11/8/16.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    /**
     * add new fragment into stack
     *
     * @param containerId
     * @param fragment
     */
    public void addFragment(int containerId, Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.

            transaction.add(containerId, fragment, fragmentTag);
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(backStateName);
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * replace fragment by fragment
     *
     * @param containerId
     * @param fragment
     */
    public void replaceFragment(int containerId, Fragment fragment, boolean addToBackStack) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP ) {
            android.transition.Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform);
            android.transition.Transition explodeTransform = TransitionInflater.from(this).
                    inflateTransition(android.R.transition.explode);
//              fragment.setSharedElementEnterTransition(transition);
//            transaction.addSharedElement(ivBookImage,"bookImage");
        }
//        if (fragmentManager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
        transaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        transaction.replace(containerId, fragment, fragmentTag);
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addToBackStack) {
            transaction.addToBackStack(backStateName);
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
    }
}
