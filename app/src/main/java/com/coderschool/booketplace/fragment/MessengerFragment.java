package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.adapters.MessengerAdapter;
import com.coderschool.booketplace.models.Messenger;
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

import static com.coderschool.booketplace.utils.DateUtils.convertDateTime;

/**
 * Created by vinh on 11/15/16.
 */

public class MessengerFragment extends BaseFragmemt {

    public static final String MESSENGER = "users-messenger";
    public static final String USERS = "users";

    @BindView(R.id.rvMessengers)
    RecyclerView rvMessengers;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;


    DatabaseReference mMessengerDatabaseRef;
    DatabaseReference mUserDatabaseRef;
    FirebaseAuth auth;
    FirebaseUser user;

    ArrayList<Messenger> mMessengers;
    MessengerAdapter aMessgengers;

    public static MessengerFragment newInstance() {
        Bundle args = new Bundle();

        MessengerFragment fragment = new MessengerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        mMessengerDatabaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(MESSENGER)
                .child(user.getUid());
        mUserDatabaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_messenger, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getBaseActivity(), "Messenger", Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this, view);
//        mActivity.setSupportActionBar(toolbar);

        mActivity.getSupportActionBar().setTitle("Messenger");

        mMessengers = new ArrayList<>();

        aMessgengers = new MessengerAdapter(getContext(), mActivity, mMessengers);
        rvMessengers.setAdapter(aMessgengers);
        rvMessengers.setLayoutManager(new LinearLayoutManager(getContext()));

        mMessengerDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot query : dataSnapshot.getChildren()) {
                    // for current solution with simple object on query
                    final Messenger messenger = query.getValue(Messenger.class);
                    mUserDatabaseRef.child(messenger.getUid())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    messenger.setUser(user);


                                    int position = 0;
                                    for (int i = 0; i < mMessengers.size(); i++) {
                                        String strChat = convertDateTime(messenger.getDate());
                                        String strChats = convertDateTime(mMessengers.get(i).getDate());
                                        if (strChat.compareTo(strChats) > 0) {
//                                            i = mMessengers.size();
                                            break;
                                        }
                                        position++;
                                    }

                                    mMessengers.add(position, messenger);

                                    aMessgengers.notifyItemInserted(position);
//                                    rvMessageItem.scrollToPosition(chats.size() - 1);
//
//
//
//                                    mMessengers.add(messenger);
//                                    aMessgengers.notifyItemChanged(mMessengers.size() - 1);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
//        getBaseActivity().getSupportActionBar().setTitle("Messenger");
    }
}

