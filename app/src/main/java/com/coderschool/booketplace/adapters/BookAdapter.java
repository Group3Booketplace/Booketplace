package com.coderschool.booketplace.adapters;

import android.view.View;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.listener.OnItemListener;
import com.coderschool.booketplace.models.Book;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by dattran on 11/16/16.
 */

public class BookAdapter extends FirebaseRecyclerAdapter<Book, BookVH> {

    private OnItemListener mOnItemListener;

    public BookAdapter(Query ref) {
        super(Book.class, R.layout.item_book, BookVH.class, ref);
    }

    @Override
    protected void populateViewHolder(BookVH viewHolder, Book model, int position) {
        viewHolder.bind(model);
        viewHolder.itemView.setOnClickListener(v -> {
            if (mOnItemListener != null) {
                mOnItemListener.onItemClick(model);
            }
        });
    }

    public void setOnItemListener(OnItemListener listener) {
        mOnItemListener = listener;
    }

}
