package com.coderschool.booketplace.api;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.utils.BitmapUtils;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

    public void writeNewBook(Book book, Bitmap bitmap, FirebaseResultListener listener) {
        String key = bookDatabaseRef.push().getKey();
        UploadTask task = bookStorageRef.child(key).putBytes(BitmapUtils.steamFromBitmap(bitmap));
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = taskSnapshot.getDownloadUrl().toString();
                book.setImages(url);
                book.setUser(user.getUid());
                bookDatabaseRef.child(key).setValue(book.toMap());
                listener.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    listener.onFail();
            }
        });
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
