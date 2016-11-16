package com.coderschool.booketplace.api;

import com.coderschool.booketplace.models.Book;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by dattran on 11/16/16.
 */

public class FirebaseApi {
    private static final String TAG = FirebaseApi.class.getSimpleName();

    // singleton firebase instance
    private static volatile FirebaseApi sInstance;

    // firebase property
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference bookRef;

    // firebase key
    private static final String BOOKS = "books";

    public static FirebaseApi getInstance() {
        if (sInstance == null) {
            synchronized (TAG) {
                if (sInstance == null) {
                    sInstance = new FirebaseApi();
                }
            }
        }
        return sInstance;
    }

    private FirebaseApi() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        bookRef = database.getReference().child(BOOKS);
    }

    public void writeNewBook(Book book) {
        String key = bookRef.push().getKey();
        bookRef.child(key).setValue(book);
    }

    public void logout() {
        auth.signOut();
        LoginManager.getInstance().logOut();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }
}
