package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by duongthoai on 11/8/16.
 */

public class DetailFragment extends BaseFragmemt {

    @BindView(R.id.tv_home_detail)
    TextView detail;

    public static DetailFragment newInstance() {

        Bundle args = new Bundle();

        DetailFragment fragment = new DetailFragment();
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
        Toast.makeText(getBaseActivity(), "detail", Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().getSupportActionBar().setTitle("Detail");
    }
}
