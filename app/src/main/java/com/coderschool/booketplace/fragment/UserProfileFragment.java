package com.coderschool.booketplace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.booketplace.BaseFragmemt;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.coderschool.booketplace.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dattran on 11/18/16.
 */

public class UserProfileFragment extends BaseFragmemt {
    private static final String UID = "uid";

    public static UserProfileFragment newInstance(String uid) {

        Bundle args = new Bundle();
        args.putString(UID, uid);

        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.ivProfile)
    ImageView ivProfile;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvLocation)
    TextView tvLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupUser();
    }

    private void setupUser() {
        String uid = getArguments().getString(UID);
        FirebaseApi.getInstance().getUserDatabaseRef().child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Picasso.with(mActivity).load(user.getAvatar()).into(ivProfile);
                tvName.setText(user.getName());
                tvLocation.setText(user.getAddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
