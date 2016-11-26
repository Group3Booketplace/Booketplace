package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duongthoai on 11/8/16.
 */

public class HomeFragment extends BaseFragmemt {

    @BindView(R.id.tvName)
    TextView detail;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
//        getBaseActivity().getSupportActionBar().setTitle("Home");
    }

//    @OnClick(R.id.tvName)
//    public void onDetailClicked() {
//        if(getBaseActivity() != null) {
//            getBaseActivity().replaceFragment(R.id.frame, DetailFragment.newInstance(), false);
//        }
//    }
}
