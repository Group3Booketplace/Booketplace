package com.coderschool.booketplace.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.fragment.DetailFragment;
import com.coderschool.booketplace.models.Book;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dattran on 11/28/16.
 */

public class DetailActivity extends BaseActivity {
    private static final String BOOK = "book";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnChat)
    Button btnChat;
    private Book book;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupView(savedInstanceState);
    }

    private void setupView(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        book = Parcels.unwrap(getIntent().getParcelableExtra(BOOK));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containder, DetailFragment.newInstance(book))
                    .commit();
        }
    }

    public static Intent getIntent(Context context, Book book) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(BOOK, Parcels.wrap(book));
        return intent;
    }

    @OnClick(R.id.btnChat)
    public void onChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("chat", book.getUser());
        startActivity(intent);
    }


}
