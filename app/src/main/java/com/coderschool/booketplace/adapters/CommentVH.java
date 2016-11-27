package com.coderschool.booketplace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coderschool.booketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dattran on 11/27/16.
 */

public class CommentVH extends RecyclerView.ViewHolder {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvComment)
    TextView tvComment;
    @BindView(R.id.tvTime)
    TextView tvTime;

    public CommentVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
