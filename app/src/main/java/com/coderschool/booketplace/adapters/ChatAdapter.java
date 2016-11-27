package com.coderschool.booketplace.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.Chat;
import com.coderschool.booketplace.views.ChatOwnItemViewHolder;
import com.coderschool.booketplace.views.ChatUserItemViewHolder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static com.coderschool.booketplace.utils.DateUtils.getRelativeTimeAgo;

/**
 * Created by vinh on 11/15/16.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int FRIEND_MESSAGE_ITEM = 0;
    private final int OWN_MESSAGE_ITEM = 1;

    private Context mContext;
    private ArrayList<Chat> chats;

    public ChatAdapter(Context context, ArrayList<Chat> chats) {
        this.chats = chats;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        String currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (chats.get(position).getUser().getUid().equals(currentUid)) {
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
                viewHolder = new ChatOwnItemViewHolder(vOwnerMessageItem);
                break;
            case FRIEND_MESSAGE_ITEM:
                View vFriendMessageItem
                        = inflater.inflate(R.layout.item_friend_message, parent, false);
                viewHolder = new ChatUserItemViewHolder(vFriendMessageItem);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case OWN_MESSAGE_ITEM:
                ChatOwnItemViewHolder vhOwnItem = (ChatOwnItemViewHolder) holder;
                configureOwnViewHolder(vhOwnItem, position);
                break;
            case FRIEND_MESSAGE_ITEM:
                ChatUserItemViewHolder vhUserItem = (ChatUserItemViewHolder) holder;
                configureFriendViewHolder(vhUserItem, position);
                break;
        }
    }

    private void configureFriendViewHolder(ChatUserItemViewHolder viewHolder, int position) {
        Chat chat = chats.get(position);
        if (chat != null) {
            viewHolder.getTvContent().setText(chat.getContent());
            viewHolder.getTvDate().setText(getRelativeTimeAgo(chat.getDate()));
            Glide.with(mContext)
                    .load(chat.getUser().getAvatar())
                    .into(viewHolder.getIvAvatar());
        }
    }

    private void configureOwnViewHolder(ChatOwnItemViewHolder viewHolder, int position) {
        Chat chat = chats.get(position);
        if (chat != null) {
            viewHolder.getTvContent().setText(chat.getContent());
            viewHolder.getTvDate().setText(getRelativeTimeAgo(chat.getDate()));
            Glide.with(mContext)
                    .load(chat.getUser().getAvatar())
                    .asBitmap().centerCrop()
                    .into(new BitmapImageViewTarget(viewHolder.getIvAvatar()) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            viewHolder.getIvAvatar().setImageDrawable(circularBitmapDrawable);
                        }
                    });
//                    .into(viewHolder.getIvAvatar());
        }
    }

    @Override
    public int getItemCount() {
        return this.chats.size();
    }
}









