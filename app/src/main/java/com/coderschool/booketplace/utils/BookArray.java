package com.coderschool.booketplace.utils;

import com.coderschool.booketplace.models.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by dattran on 11/23/16.
 */

public class BookArray implements ValueEventListener {
    private DatabaseReference mDatabaseReference;
    private ArrayList<Book> books;

    public BookArray(Query query) {
        mDatabaseReference.limitToLast(10).addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Book book = dataSnapshot.getValue(Book.class);
        books.add(book);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    public void nextPage() {
        mDatabaseReference.endAt(books.get(books.size()).getKey()).limitToLast(10);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
