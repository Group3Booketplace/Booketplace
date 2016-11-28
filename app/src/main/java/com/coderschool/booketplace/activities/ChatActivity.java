package com.coderschool.booketplace.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.ChatAdapter;
import com.coderschool.booketplace.models.Chat;
import com.coderschool.booketplace.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.coderschool.booketplace.utils.DateUtils.convertDateTime;
import static com.coderschool.booketplace.utils.DateUtils.getStringDate;

public class ChatActivity extends AppCompatActivity {

    public static final String CHAT = "users-chat";
    public static final String USERS = "users";

    @BindView(R.id.rvMessageItem)
    RecyclerView rvMessageItem;
    @BindView(R.id.btnSend)
    TextView btnSend;
    @BindView(R.id.etChatMessage)
    EditText etChatMessage;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    DatabaseReference mChatDatabaseRef;
    DatabaseReference mUserDatabaseRef;
    DatabaseReference mMessengerDatabaseRef;
    FirebaseAuth auth;
    FirebaseUser user;

    ValueEventListener chatEventListener;

    String uniqueKey;
    ArrayList<Chat> chats;
    ChatAdapter messageItemAdapter;
    String friendUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        friendUid = getIntent().getStringExtra("chat");
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
        mMessengerDatabaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("users-messenger")
                .child(friendUid);

        chats = new ArrayList<>();
        messageItemAdapter = new ChatAdapter(this, chats);
        rvMessageItem.setAdapter(messageItemAdapter);
        rvMessageItem.setLayoutManager(new LinearLayoutManager(this));
        rvMessageItem.scrollToPosition(chats.size() - 1);

        mUserDatabaseRef.child(friendUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                getSupportActionBar().setTitle(user.getName());
                getSupportActionBar().setSubtitle("Online");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        etChatMessage.requestFocus();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        etChatMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                {
                    rvMessageItem.scrollToPosition(chats.size() - 1);
                }
            }
        });


        etChatMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etChatMessage.getText().toString().equals("")) {
                    String date = getStringDate();
                    String message = etChatMessage.getText().toString();
                    Map<String, Object> childUpdate = new HashMap<>();

                    Map<String, Object> newChatMessage = (new Chat(user.getUid(),
                            etChatMessage.getText().toString(),
                            date)
                    ).toMap();
                    childUpdate = new HashMap<>();
                    String key = mChatDatabaseRef.push().getKey();
                    childUpdate.put("/users-chat/" + uniqueKey + "/" + key, newChatMessage);
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .updateChildren(childUpdate);
                    etChatMessage.setText("");

                    FirebaseDatabase.getInstance().getReference()
                            .child("users-messenger")
                            .child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot query : dataSnapshot.getChildren()) {
                                String uid = query.child("uid").getValue().toString();
                                if (uid.equals(friendUid)) {
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("users-messenger")
                                            .child(user.getUid())
                                            .child(query.getKey())
                                            .child("date")
                                            .setValue(date);
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("users-messenger")
                                            .child(user.getUid())
                                            .child(query.getKey())
                                            .child("lastMessage")
                                            .setValue(message);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    FirebaseDatabase.getInstance().getReference()
                            .child("users-messenger")
                            .child(friendUid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot query : dataSnapshot.getChildren()) {
                                String uid = query.child("uid").getValue().toString();
                                if (uid.equals(user.getUid())) {
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("users-messenger")
                                            .child(friendUid)
                                            .child(query.getKey())
                                            .child("date")
                                            .setValue(date);
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("users-messenger")
                                            .child(friendUid)
                                            .child(query.getKey())
                                            .child("lastMessage")
                                            .setValue(message);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

//                    // update last message for current user
//                    Map<String, Object> newLastMessage = (
//                            new Messenger(friendUid, date, etChatMessage.getText().toString()))
//                            .toMap();
//                    childUpdate = new HashMap<>();
//                    childUpdate.put("/users-messenger/" + user.getUid() + "/" + )

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mMessengerDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean flag = false;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data != null) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    String userMessengerKey = mMessengerDatabaseRef.push().getKey();
                    Map<String, String> newMessenger = new HashMap<>();
                    newMessenger.put("uid", auth.getCurrentUser().getUid());
                    Map<String, Object> childUpdate = new HashMap<>();
                    childUpdate.put(userMessengerKey, newMessenger);
                    mMessengerDatabaseRef.updateChildren(childUpdate);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mChatDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Chat chat = dataSnapshot.getValue(Chat.class);
                mUserDatabaseRef.child(chat.getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                User user = dataSnapshot.getValue(User.class);
                                chat.setUser(user);



                                int position = 0;
                                for (int i = 0; i < chats.size(); i++) {
                                    String strChat = convertDateTime(chat.getDate());
                                    String strChats = convertDateTime(chats.get(i).getDate());
                                    if (strChat.compareTo(strChats) < 0) {
                                        i = chats.size();
                                    }
                                    position++;
                                }

                                chats.add(position, chat);

                                messageItemAdapter.notifyItemInserted(position);
                                rvMessageItem.scrollToPosition(chats.size() - 1);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSoftKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
}

