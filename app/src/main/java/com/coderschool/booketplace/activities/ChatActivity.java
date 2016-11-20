package com.coderschool.booketplace.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.MessageItemAdapter;
import com.coderschool.booketplace.models.MessageItem;
import com.coderschool.booketplace.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    public static final String CHAT = "users-chat";
    public static final String USERS = "users";

    @BindView(R.id.rvMessageItem)
    RecyclerView rvMessageItem;

    DatabaseReference mChatDatabaseRef;
    DatabaseReference mUserDatabaseRef;
    FirebaseAuth auth;
    FirebaseUser user;

    String uniqueKey;
    ArrayList<MessageItem> messageItems;
    MessageItemAdapter messageItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        String friendUid = getIntent().getStringExtra("chat");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        uniqueKey = user.getUid().compareTo(friendUid) > 0 ?
                friendUid + "-" + user.getUid() :
                user.getUid() + "-" + friendUid;
        mChatDatabaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(CHAT)
                .child(uniqueKey);
        mUserDatabaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS);

        messageItems = new ArrayList<>();
        messageItemAdapter = new MessageItemAdapter(this, messageItems);
        rvMessageItem.setAdapter(messageItemAdapter);
        rvMessageItem.setLayoutManager(new LinearLayoutManager(this));
        rvMessageItem.scrollToPosition(messageItems.size() - 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mChatDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot query : dataSnapshot.getChildren()) {
                    final MessageItem messageItem = query.getValue(MessageItem.class);
                    mUserDatabaseRef.child(messageItem.getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    messageItem.setUser(user);
                                    messageItems.add(messageItem);
                                    messageItemAdapter.notifyItemInserted(messageItems.size() - 1);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
