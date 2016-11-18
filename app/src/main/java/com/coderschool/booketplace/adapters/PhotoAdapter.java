package com.coderschool.booketplace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.coderschool.booketplace.BaseActivity;
import com.coderschool.booketplace.R;
import com.coderschool.booketplace.api.FirebaseApi;
import com.firebase.ui.storage.images.FirebaseImageLoader;

/**
 * Created by dattran on 11/18/16.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoVH> {
    private BaseActivity mActivity;
    private String key;
    private int count;


    public PhotoAdapter(BaseActivity activity, String key, int count) {
        this.mActivity = activity;
        this.key = key;
        this.count = count;
    }

    @Override
    public PhotoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_photo, parent, false);
        return new PhotoVH(view);
    }

    @Override
    public void onBindViewHolder(PhotoVH holder, int position) {
        Glide.with(mActivity)
                .using(new FirebaseImageLoader())
                .load(FirebaseApi.getInstance().getBookImageStorage(key, position))
                .into(holder.ivBook);
    }

    @Override
    public int getItemCount() {
        return count;
    }
}
