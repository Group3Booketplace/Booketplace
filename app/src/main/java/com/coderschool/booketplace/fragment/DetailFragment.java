package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.models.User;
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
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvSeller)
    TextView tvSeller;

    private Book book;
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
        Glide.with(mActivity)
                .load(book.getImage().getUrl())
                .into(ivPhoto);
        getUser();
    }

    private void getUser() {
        FirebaseApi.getInstance().getUserDatabaseRef().child(book.getUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                setUser(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUser(User user) {
//        ForegroundColorSpan accentColor = new ForegroundColorSpan(mActivity.getResources().getColor(R.color.colorAccent));
//        String seller = "Sold by " + user.getName() + " " + DateUtils.getRelativeTimeAgo(book.getCreatedDate());
//        SpannableString spannableString = new SpannableString(seller);
//        spannableString.setSpan(accentColor, 8, user.getName().length() + 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvSeller.setText(user.getName());
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

    @OnClick(R.id.btnChat)
    public void onChat() {

    }

}
