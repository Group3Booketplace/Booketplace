package com.coderschool.booketplace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.utils.Event;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dattran on 11/24/16.
 */

public class CategoryVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.ivCategory)
    ImageView ivCategory;
    @BindView(R.id.tvCategory)
    TextView tvCategory;

    public CategoryVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().post(new Event.CategoryClick(tvCategory.getText().toString()));
    }
}
