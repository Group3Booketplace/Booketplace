package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.PhotoAdapter;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.models.User;
import com.coderschool.booketplace.utils.DateUtils;
import com.coderschool.booketplace.utils.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.tvSeller)
    TextView tvSeller;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    private Book book;

    private LinearLayoutManager mLayoutManager;
    private PhotoAdapter mAdapter;

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
        book = Parcels.unwrap(getArguments().getParcelable(EXTRA_BOOK));
        tvName.setText(book.getName());
        tvPrice.setText(book.getPrice());
        tvDescription.setText(book.getDescription());
        mAdapter = new PhotoAdapter(mActivity, book.getKey(), book.getImages().size());
        mLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        rvPhoto.setAdapter(mAdapter);
        rvPhoto.setLayoutManager(mLayoutManager);
        getUser(book);
    }

    private void getUser(Book book) {
        FirebaseApi.getInstance().getUserDatabaseRef().child(book.getUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                setUser(user, book);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUser(User user, Book book) {
        ForegroundColorSpan accentColor = new ForegroundColorSpan(mActivity.getResources().getColor(R.color.colorAccent));
        String seller = "Sold by " + user.getName() + " " + DateUtils.getRelativeTimeAgo(book.getCreatedDate());
        SpannableString spannableString = new SpannableString(seller);
        spannableString.setSpan(accentColor, 8, user.getName().length() + 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvSeller.setText(spannableString);
        ratingBar.setRating(user.getRatingOverall());
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().getSupportActionBar().setTitle("Detail");
    }

    @OnClick(R.id.tvSeller)
    public void onUser() {
        EventBus.getDefault().post(new Event.UserClick(book.getUser()));
    }


}
