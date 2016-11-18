package com.coderschool.booketplace.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by dattran on 11/18/16.
 */

public class PhotoVH extends RecyclerView.ViewHolder {
    ImageView ivBook;
    public PhotoVH(View itemView) {
        super(itemView);
        ivBook = (ImageView) itemView;
    }

}
