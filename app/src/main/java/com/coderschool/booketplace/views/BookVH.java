package com.coderschool.booketplace.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.models.User;
import com.coderschool.booketplace.utils.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dattran on 11/16/16.
 */

public class BookVH extends RecyclerView.ViewHolder {
    @BindView(R.id.ivBook)
    ImageView ivBook;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
//    @BindView(R.id.tvDescription)
//    TextView tvDescription;
    @BindView(R.id.tvCondition)
    TextView tvCondition;
    @BindView(R.id.tvSeller)
    TextView tvSeller;
    @BindView(R.id.ivFrame)
    FrameLayout ivFrame;
    Book book;

    public BookVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Book book) {
        this.book = book;
        tvName.setText(book.getName());
        tvPrice.setText(book.getPrice());
//        tvDescription.setText(book.getDescription());
        tvCondition.setText(book.getCondition());
        ViewGroup.LayoutParams layoutParams = ivBook.getLayoutParams();


        Glide.with(itemView.getContext())
                .load(book.getImage().getUrl())
//                .placeholder(BitmapUtils.placeholder(itemView.getContext(), book.getImage(), itemView.getWidth()))
                .into(ivBook);
        FirebaseApi.getInstance().getUserDatabaseRef().child(book.getUser()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
//                tvSeller.setText("Sold by " + user.getName() + " " + DateUtils.getRelativeTimeAgo(book.getCreatedDate()));
                tvSeller.setText(user.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.itemBook)
    public void onItemClick() {
        EventBus.getDefault().post(new Event.ItemBookClick(book));
    }

}
