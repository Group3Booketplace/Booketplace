package com.coderschool.booketplace.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.FollowingAdapter;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.fragment.BookStreamFragment;
import com.coderschool.booketplace.fragment.DetailFragment;
import com.coderschool.booketplace.fragment.FollowingFragment;
import com.coderschool.booketplace.fragment.HomeFragment;
import com.coderschool.booketplace.fragment.MessengerFragment;
import com.coderschool.booketplace.fragment.NewPostFragment;
import com.coderschool.booketplace.fragment.SettingFragment;
import com.coderschool.booketplace.fragment.UserProfileFragment;
import com.coderschool.booketplace.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.coderschool.booketplace.R.id.fab;


public class MainActivity extends BaseActivity implements FollowingAdapter.OnFollowingUserListener {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private View navHeader;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // urls to load navigation header background image
    // and profile image
    // index to identify current nav menu item


    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    //    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_MESSAGES = "messages";
    private static final String TAG_PROFLE = "profile";
    //    private static final String TAG_SUBSCRIBE = "subscribe";
//    private static final String TAG_PEOPLE = "people";
    private static final String TAG_FOLLOWING = "following";
    private static final String TAG_SETTINGS = "settings";
//    private static final String TAG_ACCOUNT = "account";


    public static String CURRENT_TAG = TAG_HOME;

    private static final int INDEX_HOME = 0;
    private static final int INDEX_MESSAGE = 1;
    //    private static final int INDEX_ACCOUNT = 1;
    private static final int INDEX_PROFILE = 2;
    private static final int INDEX_FOLLOWING = 3;
    private static final int INDEX_SETTINGS = 4;
    private static final int INDEX_SIGNOUT = 5;
    public static int navItemIndex = INDEX_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = INDEX_HOME;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); // show back button
                toolbar.setNavigationOnClickListener(v -> onBackPressed());
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            } else {
                //show hamburger
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                actionBarDrawerToggle.syncState();
                toolbar.setNavigationOnClickListener(v -> {
                    drawer.openDrawer(GravityCompat.START);
                });
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = () -> {
            // update the main content by replacing fragments
            replaceFragment(R.id.frame, getHomeFragment(), false);
            //
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case INDEX_HOME:
                return BookStreamFragment.newInstance(FirebaseApi.BOOKS, "");
            case INDEX_MESSAGE:
                return MessengerFragment.newInstance();
            case INDEX_PROFILE: // INDEX_MESSAGE
                return UserProfileFragment.newInstance(FirebaseApi.getInstance().getUser().getUid());
            // TODO: Setting & Following
            case INDEX_FOLLOWING:
                return FollowingFragment.newInstance();
            default:
                return HomeFragment.newInstance();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            //Check to see which item was being clicked and perform appropriate action
            //Check to see which item was being clicked and perform appropriate action
            switch (menuItem.getItemId()) {
                //Replacing the main content with ContentFragment Which is our Inbox View;
                case R.id.nav_home:
                    navItemIndex = INDEX_HOME;
                    CURRENT_TAG = TAG_HOME;
                    break;
                case R.id.nav_messages:
                    navItemIndex = INDEX_MESSAGE;
                    CURRENT_TAG = TAG_MESSAGES;
                    break;
                case R.id.nav_profile:
                    navItemIndex = INDEX_PROFILE;
                    CURRENT_TAG = TAG_PROFLE;
                    replaceFragment(R.id.frame,
                            UserProfileFragment.newInstance(FirebaseApi.getInstance().getUser().getUid()),
                            true);
                    break;
                case R.id.nav_following:
                    navItemIndex = INDEX_FOLLOWING;
                    CURRENT_TAG = TAG_FOLLOWING;
                    break;
                case R.id.nav_settings:
                    navItemIndex = INDEX_SETTINGS;
                    CURRENT_TAG = TAG_SETTINGS;
                    // TODO: show setting
                    showSetting();
                    drawer.closeDrawers();
                    return true;
                case R.id.nav_signout:
                    FirebaseApi.getInstance().logout();
                    startActivity(LoginActivity.getIntent(this));
                    drawer.closeDrawers();
                    return true;
                case R.id.nav_about_us:
                    drawer.closeDrawers();
                    return true;
                default:
                    navItemIndex = INDEX_HOME;
            }

            //Checking if the item is in checked state or not, if not make it in checked state
            if (menuItem.isChecked()) {
                menuItem.setChecked(false);
            } else {
                menuItem.setChecked(true);
            }

            //Checking if the item is in checked state or not, if not make it in checked state
            if (menuItem.isChecked()) {
                menuItem.setChecked(false);
            } else {

                menuItem.setChecked(true);
            }
            menuItem.setChecked(true);

            loadHomeFragment();

            return true;
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void showSetting() {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame, SettingFragment.newInstance())
                .commit();
        // TODO: clear support fragment
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick(fab)
    public void newPost(View view) {
        // Show new post fragment
        if (FirebaseApi.getInstance().getUser() == null) {
            // TODO: show a dialog said that user need to sign in first
            startActivity(LoginActivity.getIntent(this));
        } else {
            replaceFragment(R.id.frame, NewPostFragment.newInstance(), true);
            toolbar.setTitle(R.string.manga_sell);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event.ItemBookClick event) {
        replaceFragment(R.id.frame, DetailFragment.newInstance(event.getBook()), true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEven(Event.UserClick event) {
        replaceFragment(R.id.frame, UserProfileFragment.newInstance(event.getUser()), true);
    }

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
}
