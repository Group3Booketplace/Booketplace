package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duongthoai on 11/27/16.
 */

public class CategoryDetailFragment extends BaseFragmemt {

    private static final String CATEGORY = "category";

    public static CategoryDetailFragment newInstance(String category) {

        Bundle args = new Bundle();

        CategoryDetailFragment fragment = new CategoryDetailFragment();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_detail_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        String[] categories = getResources().getStringArray(R.array.spinner_category);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //TODO: change fragment here\\\\\
        adapter.addFragment(CategoryBookStreamFragment.newInstance("manga"), categories[0]);
        adapter.addFragment(CategoryBookStreamFragment.newInstance("manga"), categories[1]);
        adapter.addFragment(CategoryBookStreamFragment.newInstance("manga"), categories[2]);
        adapter.addFragment(CategoryBookStreamFragment.newInstance("manga"), categories[3]);
        adapter.addFragment(CategoryBookStreamFragment.newInstance("manga"), categories[4]);
        adapter.addFragment(CategoryBookStreamFragment.newInstance("manga"), categories[5]);
        viewPager.setAdapter(adapter);
        //set selected item
        String category = getArguments().getString(CATEGORY);
        int pos = 0;
        int size = categories.length;
        for (int i = 0; i < size; i++) {
            if (category.toLowerCase().equals(categories[i].toLowerCase())) {
                pos = i;
                break;
            }
        }
        final int temp = pos;
        viewPager.postDelayed(() -> viewPager.setCurrentItem(temp, true), 100);
    }
}
