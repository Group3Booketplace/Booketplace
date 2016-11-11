package com.coderschool.booketplace.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.fragment.HomeFragment;
import com.coderschool.booketplace.fragment.NewPostFragment;
import com.coderschool.booketplace.fragment.SettingFragment;
import com.facebook.login.LoginManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.transition.Fade.IN;
import static com.coderschool.booketplace.R.id.fab;


public class HomeActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private View navHeader;
//    private ImageView imgNavHeaderBg, imgProfile;
//    private TextView txtName, txtWebsite;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // urls to load navigation header background image
    // and profile image
    // index to identify current nav menu item


    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_FOLLOWING = "following";
    private static final String TAG_SETTING = "setting";
    private static final String TAG_SIGNOUT = "signout";
    public static String CURRENT_TAG = TAG_HOME;

    private static final int INDEX_HOME = 0;
    private static final int INDEX_ACCOUNT = 1;
    private static final int INDEX_FOLLOWING = 2;
    private static final int INDEX_SETTING = 3;
    private static final int INDEX_SIGNOUT = 4;
    public static int navItemIndex = INDEX_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                replaceFragment(getHomeFragment());
            }
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

    public void replaceFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case INDEX_HOME:
                // home
                HomeFragment homeFragment = HomeFragment.newInstance();
                return homeFragment;
            case INDEX_ACCOUNT:
                HomeFragment homeFragment1 = HomeFragment.newInstance();
                return homeFragment1;
            default:
                return null;
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
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        navItemIndex = INDEX_HOME;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_account:
                        navItemIndex = INDEX_ACCOUNT;
                        CURRENT_TAG = TAG_ACCOUNT;
                        break;
                    case R.id.nav_following:
                        navItemIndex = INDEX_FOLLOWING;
                        CURRENT_TAG = TAG_FOLLOWING;
                        break;
                    case R.id.nav_setting:
                        navItemIndex = INDEX_SETTING;
                        CURRENT_TAG = TAG_SETTING;
                        // TODO: show setting
                        showSetting();
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_signout:
                        mAuth.signOut();
                        LoginManager.getInstance().logOut();
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_about_us:
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
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
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
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
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
//        if (navItemIndex == 0) {
//            getMenuInflater().inflate(R.menu.main, menu);
//        }
//
//         when fragment is notifications, load the menu created for notifications
//        if (navItemIndex == 3) {
//            getMenuInflater().inflate(R.menu.notifications, menu);
//        }
        return true;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return  intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick(fab)
    public void newPost(View view) {
        // Show new post fragment
        if (mUser == null) {
            // TODO: show a dialog said that user need to sign in first
            startActivity(LoginActivity.getIntent(this));
        } else {
            replaceFragment(NewPostFragment.newInstance());
            toolbar.setTitle(R.string.manga_sell);
        }
    }
}
