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
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fetch data from firebase with filter
 * Created by dattran on 11/16/16.
 */

public class BookStreamFragment extends BaseFragmemt {
    private static final String PARENT = "parent";
    private static final String CHILD = "child";

    @BindView(R.id.rvBooks)
    RecyclerView rvBooks;
    private BookAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public static BookStreamFragment newInstance(String parent, String child) {

        Bundle args = new Bundle();
        args.putString(PARENT, parent);
        args.putString(CHILD, child);

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
        Bundle bundle = getArguments();
        DatabaseReference reference = FirebaseApi
                .getInstance()
                .getDatabaseReference()
                .child(bundle.getString(PARENT))
                .child(bundle.getString(CHILD));
        mAdapter = new BookAdapter(reference);
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(mLayoutManager);
        rvBooks.setAdapter(mAdapter);
    }
}
