package com.coderschool.booketplace.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.MessageItemAdapter;
import com.coderschool.booketplace.models.MessageItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.rvMessageItem)
    RecyclerView rvMessageItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        ArrayList<MessageItem> messageItems = new ArrayList<>();
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleFriendChat());
        messageItems.add(MessageItem.createExampleOwnChat());
        messageItems.add(MessageItem.createExampleFriendChat());

        MessageItemAdapter messageItemAdapter
                = new MessageItemAdapter(this, messageItems);
        rvMessageItem.setAdapter(messageItemAdapter);
        rvMessageItem.setLayoutManager(new LinearLayoutManager(this));
        rvMessageItem.scrollToPosition(messageItems.size() - 1);
    }
}
