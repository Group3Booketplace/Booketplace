package com.coderschool.booketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.activities.ChatActivity;
import com.coderschool.booketplace.models.User;
import com.coderschool.booketplace.views.MessageViewHolder;

import java.util.ArrayList;

/**
 * Created by vinh on 11/15/16.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static String EXTRA_CHAT = "chat";

    private ArrayList<User> mUsers;
    private Context mContext;

    public MessageAdapter(Context context, ArrayList<User> users) {
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
                .inflate(R.layout.item_messages, parent, false);
        return new MessageViewHolder(context, messageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final User user = mUsers.get(position);

        ((MessageViewHolder)viewHolder).getUsername().setText(user.getName());
        Glide.with(mContext)
                .load(user.getAvatar())
                .into(((MessageViewHolder)viewHolder).getAvatar());

        ((MessageViewHolder)viewHolder).itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ChatActivity.class);
                        intent.putExtra(EXTRA_CHAT, user.getUid());
                        getContext().startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
















