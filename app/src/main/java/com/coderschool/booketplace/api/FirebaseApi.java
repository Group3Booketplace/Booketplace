package com.coderschool.booketplace.api;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.models.Image;
import com.coderschool.booketplace.utils.BitmapUtils;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

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
    private DatabaseReference bookDatabaseRef;
    private FirebaseStorage storage;
    private StorageReference bookStorageRef;

    // firebase key
    private static final String BOOKS = "books";

    // interface to return result
    public interface FirebaseResultListener {
        public void onSuccess();
        public void onFail();
    }

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
        bookDatabaseRef = database.getReference().child(BOOKS);
        storage = FirebaseStorage.getInstance();
        bookStorageRef = storage.getReference().child(BOOKS);
    }

    public void writeNewBook(Book book, ArrayList<Bitmap> bitmaps, FirebaseResultListener listener) {
        String key = bookDatabaseRef.push().getKey();
        book.setKey(key);
        for (int i = 0; i < bitmaps.size(); i++) {
            Bitmap bitmap = bitmaps.get(i);

            Bitmap resizedBitmap = BitmapUtils.resize(bitmap, (float) 0.1);

            UploadTask task = bookStorageRef.child(key).child(String.valueOf(i)).putBytes(BitmapUtils.steamFromBitmap(resizedBitmap));

            int width = resizedBitmap.getWidth();
            int height = resizedBitmap.getHeight();

            book.addImage(new Image(width, height));
            task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d(TAG, taskSnapshot.getDownloadUrl().toString());
//                    listener.onSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    listener.onFail();
                }
            });
            bookDatabaseRef.child(key).setValue(book);
        }


    }

    public StorageReference getImageStorageRef(String key) {
        return bookStorageRef.child(key).child("0");
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

    public DatabaseReference getBookDatabaseRef() {
        return bookDatabaseRef;
    }
}
