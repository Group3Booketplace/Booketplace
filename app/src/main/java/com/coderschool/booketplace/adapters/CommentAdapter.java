package com.coderschool.booketplace.adapters;

import android.support.v7.widget.RecyclerView;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.Comment;
import com.coderschool.booketplace.utils.DateUtils;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by dattran on 11/27/16.
 */

public class CommentAdapter extends FirebaseRecyclerAdapter<Comment, CommentVH> {

    public CommentAdapter(Query ref) {
        super(Comment.class, R.layout.item_comment, CommentVH.class, ref);
    }

    @Override
    protected void populateViewHolder(CommentVH holder, Comment comment, int position) {
        holder.tvComment.setText(comment.getText());
        holder.tvName.setText(comment.getUser());
        holder.tvTime.setText(DateUtils.getRelativeTimeAgo(comment.getTime()));
        Picasso.with(holder.itemView.getContext())
                .load(comment.getAvatar())
                .transform(new CropCircleTransformation())
                .into(holder.ivAvatar);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }
}
