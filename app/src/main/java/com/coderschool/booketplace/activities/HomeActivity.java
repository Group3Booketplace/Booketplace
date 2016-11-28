package com.coderschool.booketplace.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;

import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.FollowingAdapter;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.fragment.CategoryDetailFragment;
import com.coderschool.booketplace.fragment.CategoryFragment;
import com.coderschool.booketplace.fragment.MessengerFragment;
import com.coderschool.booketplace.fragment.UserProfileFragment;
import com.coderschool.booketplace.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duongthoai on 11/23/16.
 */

public class HomeActivity extends BaseActivity implements FollowingAdapter.OnFollowingUserListener {

    @BindView(R.id.nav_view)
    BottomNavigationView mBottomNavigationMenu;
    private Handler mHandler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        addFragment(R.id.frame, CategoryFragment.newInstance());
        mBottomNavigationMenu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(R.id.frame, CategoryFragment.newInstance(), false);
                    break;
                case R.id.nav_profile:
                    replaceFragment(R.id.frame, UserProfileFragment.newInstance(FirebaseApi.getInstance().getUser().getUid()), true);
                    break;
                case R.id.nav_messages:
                    replaceFragment(R.id.frame, MessengerFragment.newInstance(), true);
                    break;
                case R.id.nav_search:
                    // TODO: future support
                    break;
                case R.id.nav_new:
                    startActivity(NewPostActivity.getIntent(this));
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

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event.ItemBookClick event) {
//        replaceFragment(R.id.frame, DetailFragment.newInstance(event.getBook()), true);
        startActivity(DetailActivity.getIntent(this, event.getBook()));
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEven(Event.UserClick event) {
//        replaceFragment(R.id.frame, UserProfileFragment.newInstance(event.getUser()), true);
//    }

    @Override
    public void setFollwingUserSelected(String link) {
        Runnable mPendingRunnable = () -> {
            replaceFragment(R.id.frame, UserProfileFragment.newInstance(link), false);
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event.CategoryClick event) {
        replaceFragment(R.id.frame, CategoryDetailFragment.newInstance(event.getCategory()), true);
    }
}
