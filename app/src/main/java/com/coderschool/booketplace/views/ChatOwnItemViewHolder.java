package com.coderschool.booketplace.views;

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

public class ChatOwnItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvDate)
    TextView tvDate;

    public ChatOwnItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getIvAvatar() {
        return ivAvatar;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public TextView getTvDate() {
        return tvDate;
    }
}
