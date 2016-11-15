package com.coderschool.booketplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.MessageItem;
import com.coderschool.booketplace.views.MessageOwnItemViewHolder;
import com.coderschool.booketplace.views.MessageUserItemViewHolder;

import java.util.ArrayList;

/**
 * Created by vinh on 11/15/16.
 */

public class MessageItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FRIEND_MESSAGE_ITEM = 0;
    private final int OWN_MESSAGE_ITEM = 1;

    private Context mContext;
    private ArrayList<MessageItem> messageItems;

    public MessageItemAdapter(Context context, ArrayList<MessageItem> messageItems) {
        this.messageItems = messageItems;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (messageItems.get(position).isOwner()) {
            return OWN_MESSAGE_ITEM;
        } else {
            return FRIEND_MESSAGE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case OWN_MESSAGE_ITEM:
                View vOwnerMessageItem
                        = inflater.inflate(R.layout.item_own_message, parent, false);
                viewHolder = new MessageOwnItemViewHolder(vOwnerMessageItem);
                break;
            case FRIEND_MESSAGE_ITEM:
                View vFriendMessageItem
                        = inflater.inflate(R.layout.item_friend_message, parent, false);
                viewHolder = new MessageUserItemViewHolder(vFriendMessageItem);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case OWN_MESSAGE_ITEM:
                MessageOwnItemViewHolder vhOwnItem = (MessageOwnItemViewHolder) holder;
                configureOwnViewHolder(vhOwnItem, position);
                break;
            case FRIEND_MESSAGE_ITEM:
                MessageUserItemViewHolder vhUserItem = (MessageUserItemViewHolder) holder;
                configureFriendViewHolder(vhUserItem, position);
                break;
        }
    }

    private void configureFriendViewHolder(MessageUserItemViewHolder viewHolder, int position) {
        MessageItem messageItem = messageItems.get(position);
        if (messageItem != null) {
            viewHolder.getTvContent().setText(messageItem.getMessage());
            viewHolder.getTvDate().setText(messageItem.getDate().toString());
            Glide.with(mContext)
                    .load(messageItem.getAvatar())
                    .into(viewHolder.getIvAvatar());
        }
    }

    private void configureOwnViewHolder(MessageOwnItemViewHolder viewHolder, int position) {
        MessageItem messageItem = messageItems.get(position);
        if (messageItem != null) {
            viewHolder.getTvContent().setText(messageItem.getMessage());
            viewHolder.getTvDate().setText(messageItem.getDate().toString());
            Glide.with(mContext)
                    .load(messageItem.getAvatar())
                    .into(viewHolder.getIvAvatar());
        }
    }

    @Override
    public int getItemCount() {
        return this.messageItems.size();
    }
}









