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
import com.coderschool.booketplace.adapters.FollowingAdapter;
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

/**
 * Created by vinh on 11/23/16.
 */

public class FollowingFragment extends BaseFragmemt {

    public static final String MESSENGER = "users-followings";
    public static final String USERS = "users";

    @BindView(R.id.rvFollowings)
    RecyclerView rvFollowings;


    DatabaseReference mMessengerDatabaseRef;
    DatabaseReference mUserDatabaseRef;
    FirebaseAuth auth;
    FirebaseUser user;

    ArrayList<User> mUsers;
    FollowingAdapter aFollowings;

    public static FollowingFragment newInstance() {
        Bundle args = new Bundle();

        FollowingFragment fragment = new FollowingFragment();
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
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getBaseActivity(), "Followings", Toast.LENGTH_SHORT).show();
        ButterKnife.bind(this, view);

        mUsers = new ArrayList<>();

        aFollowings = new FollowingAdapter(getContext(), mUsers);
        rvFollowings.setAdapter(aFollowings);
        rvFollowings.setLayoutManager(new LinearLayoutManager(getContext()));

        mMessengerDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot query : dataSnapshot.getChildren()) {
                    // for current solution with simple object on query
                    String uid = query.child("uid").getValue().toString();
                    mUserDatabaseRef.child(uid)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    mUsers.add(user);
                                    aFollowings.notifyItemChanged(mUsers.size() - 1);
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
    public void onResume() {
        super.onResume();
        getBaseActivity().getSupportActionBar().setTitle("Followings");
    }
}

