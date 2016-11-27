package com.coderschool.booketplace.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.Book;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by dattran on 11/16/16.
 */

public class BookAdapter extends RecyclerView.Adapter<BookVH> {
    private Context mContext;
    private ArrayList<Book> books;

    private ChildEventListener eventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Book book = dataSnapshot.getValue(Book.class);
            books.add(0, book);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public BookAdapter(Context context, Query ref) {
        books = new ArrayList<>();
        ref.addChildEventListener(eventListener);
        mContext = context;
    }

    @Override
    public BookVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
        return new BookVH(view);
    }

    @Override
    public void onBindViewHolder(BookVH holder, int position) {
        holder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


}
