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
import com.coderschool.booketplace.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
        toolbar.setTitle("Detail");
        book = Parcels.unwrap(getIntent().getParcelableExtra(BOOK));
        replaceFragment(R.id.containder, DetailFragment.newInstance(book), true);
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

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);

        }
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event.ItemBookClick event) {
        replaceFragment(R.id.containder, DetailFragment.newInstance(event.getBook()), false);
    }
}
