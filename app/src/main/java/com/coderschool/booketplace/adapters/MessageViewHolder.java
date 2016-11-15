package com.coderschool.booketplace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.booketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vinh on 11/15/16.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvUsername) TextView tvUsername;

    public MessageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getAvatar() {
        return ivAvatar;
    }

    public TextView getUsername() {
        return tvUsername;
    }
}
