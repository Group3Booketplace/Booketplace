package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DatTran on 11/11/16.
 */

public class NewPostFragment extends BaseFragmemt {
    @BindView(R.id.iv_manga)
    ImageView ivManga;
    @BindView(R.id.et_manga_name)
    EditText etName;
    @BindView(R.id.et_manga_author)
    EditText etAuthor;
    @BindView(R.id.et_manga_price)
    EditText etPrice;
    @BindView(R.id.et_manga_description)
    EditText etDescription;
    @BindView(R.id.sp_condition)
    Spinner spCondition;

    public static NewPostFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NewPostFragment fragment = new NewPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_sell)
    public void onSell(View view) {

    }
}
