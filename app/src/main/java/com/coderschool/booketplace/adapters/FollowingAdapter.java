package com.coderschool.booketplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.User;
import com.coderschool.booketplace.views.FollowingViewHolder;

import java.util.ArrayList;

/**
 * Created by vinh on 11/15/16.
 */

public class FollowingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String EXTRA_CHAT = "user";

    private ArrayList<User> mUsers;
    private Context mContext;

    public FollowingAdapter(Context context, ArrayList<User> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View messageView = LayoutInflater.from(context)
                .inflate(R.layout.item_following, parent, false);
        return new FollowingViewHolder(context, messageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final User user = mUsers.get(position);

        ((FollowingViewHolder)viewHolder).getUsername().setText(user.getName());
        Glide.with(mContext)
                .load(user.getAvatar())
                .into(((FollowingViewHolder)viewHolder).getAvatar());

        ((FollowingViewHolder)viewHolder).itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener = (OnFollowingUserListener) getContext();
                        listener.setFollwingUserSelected(user.getUid());
                        // // TODO: 11/23/16 call user profile
                    }
                });
    }


    private OnFollowingUserListener listener;

    public interface OnFollowingUserListener {
        public void setFollwingUserSelected(String link);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
















