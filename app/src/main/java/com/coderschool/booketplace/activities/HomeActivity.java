package com.coderschool.booketplace.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.fragment.BookStreamFragment;
import com.coderschool.booketplace.fragment.FollowingFragment;
import com.coderschool.booketplace.fragment.HomeFragment;
import com.coderschool.booketplace.fragment.MessengerFragment;
import com.coderschool.booketplace.fragment.NewPostFragment;
import com.coderschool.booketplace.fragment.UserProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duongthoai on 11/23/16.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView mBottomNavigationMenu;

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        ButterKnife.bind(this);
        mHandler = new Handler();
        addFragment(R.id.frame, BookStreamFragment.newInstance(FirebaseApi.BOOKS, ""));
        mBottomNavigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(R.id.frame, BookStreamFragment.newInstance(FirebaseApi.BOOKS, ""), false);
                    break;
                case R.id.nav_profile:
                    replaceFragment(R.id.frame, UserProfileFragment.newInstance(FirebaseApi.getInstance().getUser().getUid()), true);
                    break;
                case R.id.nav_messages:
                    replaceFragment(R.id.frame, MessengerFragment.newInstance(), true);
                    break;
                case R.id.nav_search:

                    break;
                case R.id.nav_add:
                    replaceFragment(R.id.frame, NewPostFragment.newInstance(), true);
                    break;
            }
            return false;
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
//        selectNavMenu();

        // set toolbar title
//        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
//        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//
//            return;
//        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = () -> {
            // update the main content by replacing fragments
//            replaceFragment(R.id.frame, getHomeFragment(), false);
            //
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

    }
//    private Fragment getHomeFragment() {
//        switch (navItemIndex) {
//            case INDEX_HOME:
//                return BookStreamFragment.newInstance(FirebaseApi.BOOKS, "");
//            case INDEX_MESSAGE:
//                return MessengerFragment.newInstance();
//            case INDEX_PROFILE: // INDEX_MESSAGE
//                return UserProfileFragment.newInstance(FirebaseApi.getInstance().getUser().getUid());
//             TODO: Setting & Following
//            case INDEX_FOLLOWING:
//                return FollowingFragment.newInstance();
//            default:
//                return HomeFragment.newInstance();
//        }
//    }
}
