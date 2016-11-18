package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.Book;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duongthoai on 11/8/16.
 */

public class DetailFragment extends BaseFragmemt {
    private static final String EXTRA_BOOK = "extra_book";

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.rvPhoto)
    RecyclerView rvPhoto;
    @BindView(R.id.tvDescription)
    TextView tvDescription;

    public static DetailFragment newInstance(Book book) {

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_BOOK, Parcels.wrap(book));

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupBook();
    }

    private void setupBook() {
        Book book = Parcels.unwrap(getArguments().getParcelable(EXTRA_BOOK));
        tvName.setText(book.getName());
        tvPrice.setText(book.getPrice());
        tvDescription.setText(book.getDescription());
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().getSupportActionBar().setTitle("Detail");
    }
}
