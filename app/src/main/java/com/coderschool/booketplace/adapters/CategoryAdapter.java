package com.coderschool.booketplace.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.booketplace.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dattran on 11/24/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryVH> {
    private static final int[] images = {
            R.drawable.category_manga,
            R.drawable.category_kinhte,
            R.drawable.category_novel,
            R.drawable.category_suckhoe,
            R.drawable.category_ngontinh,
            R.drawable.category_china,
            R.drawable.category_selfhelp
    };
//    private static final int[] categories = {
//            R.string.manga,
//            R.string.china,
//            R.string.business,
//            R.string.love,
//            R.string.novel,
//            R.string.health
//    };

    private final String[] categories;
    private Activity mActivity;

    public CategoryAdapter(Activity activity) {
        mActivity = activity;
        categories = mActivity.getResources().getStringArray(R.array.spinner_category);
    }

    @Override
    public CategoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_category, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(CategoryVH holder, int position) {
        Picasso.with(mActivity).load(images[position]).transform(new RoundedCornersTransformation(50, 0)).into(holder.ivCategory);
        holder.ivCategory.setImageResource(images[position]);
        holder.tvCategory.setText(categories[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
