package com.coderschool.booketplace.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.activities.ChatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vinh on 11/15/16.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvUsername)
    TextView tvUsername;

    private Context context;

    public MessageViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        itemView.setOnClickListener(this);
    }

    public ImageView getAvatar() {
        return ivAvatar;
    }

    public TextView getUsername() {
        return tvUsername;
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            context.startActivity(new Intent(context, ChatActivity.class));
        }
    }
}
