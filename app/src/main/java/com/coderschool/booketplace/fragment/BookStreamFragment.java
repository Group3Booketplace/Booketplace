package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.BookAdapter;
import com.coderschool.booketplace.api.FirebaseApi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fetch data from firebase with filter
 * Created by dattran on 11/16/16.
 */

public class BookStreamFragment extends BaseFragmemt {
    @BindView(R.id.rvBooks)
    RecyclerView rvBooks;
    private BookAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public static BookStreamFragment newInstance() {

        Bundle args = new Bundle();

        BookStreamFragment fragment = new BookStreamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_stream, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupAdapter();
    }

    private void setupAdapter() {
        mAdapter = new BookAdapter(FirebaseApi.getInstance().getBookDatabaseRef());
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(mLayoutManager);
        rvBooks.setAdapter(mAdapter);
    }
}
