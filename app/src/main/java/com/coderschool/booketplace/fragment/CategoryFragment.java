package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.CategoryAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dattran on 11/24/16.
 */

public class CategoryFragment extends BaseFragmemt {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    private CategoryAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity.setSupportActionBar(toolbar);
        mAdapter = new CategoryAdapter(mActivity);
        mLayoutManager = new GridLayoutManager(mActivity, 2, LinearLayoutManager.VERTICAL, false);
        rvCategory.setAdapter(mAdapter);
        rvCategory.setLayoutManager(mLayoutManager);
    }
}
