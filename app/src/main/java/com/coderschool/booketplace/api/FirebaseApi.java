package com.coderschool.booketplace.api;

import android.graphics.Bitmap;
import android.util.Log;

import com.coderschool.booketplace.models.Book;
import com.coderschool.booketplace.models.Image;
import com.coderschool.booketplace.models.User;
import com.coderschool.booketplace.utils.BitmapUtils;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dattran on 11/16/16.
 */

public class FirebaseApi {
    private static final String TAG = FirebaseApi.class.getSimpleName();

    // singleton firebase instance
    private static volatile FirebaseApi sInstance;

    /**
     * firebase property
     */
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference bookDatabaseRef;
    private DatabaseReference userDatabaseRef;
    private FirebaseStorage storage;
    private StorageReference bookStorageRef;


    // firebase key
    private static final String BOOKS = "books";
    private static final String USERS = "users";

    /**
     * interface for asynchronous networking
     */
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

    /**
     * private constructor for singleton
     */
    private FirebaseApi() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        bookDatabaseRef = database.getReference().child(BOOKS);
        userDatabaseRef = database.getReference().child(USERS);
        storage = FirebaseStorage.getInstance();
        bookStorageRef = storage.getReference().child(BOOKS);
    }

    /**
     * Write new book to database
      * @param book
     * @param bitmaps
     * @param listener
     */
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
            task.addOnSuccessListener(taskSnapshot -> {
                Log.d(TAG, taskSnapshot.getDownloadUrl().toString());
//                    listener.onSuccess();
            }).addOnFailureListener(e -> listener.onFail());
        }
        Map<String, Object> bookValue = book.toMap();
        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put("/books/" + key, bookValue);
        childUpdate.put("/user-books/" + user.getUid() + "/" + key, bookValue);
        database.getReference().updateChildren(childUpdate);
    }


    public StorageReference getBookImageStorage(String key, int position) {
        return bookStorageRef.child(key).child(String.valueOf(position));
    }

    public void writeUser(FirebaseUser firebaseUser, AccessToken token) {
        User user = new User(firebaseUser);
        String uid = firebaseUser.getUid();

        FacebookApi.getInstance().getUserInfo(token, new FacebookApi.FacebookApiResult() {
            @Override
            public void onSuccess(Object object) {
                FacebookUser fbUser = (FacebookUser) object;
                user.setUser(fbUser);
                userDatabaseRef.child(uid).setValue(user.toMap());
            }

            @Override
            public void onFail() {

            }
        });
    }

    /**
     * login to firebase using facebook credential
     * @param token
     * @param listener
     */
    public void loginWithFacebook(AccessToken token, FirebaseResultListener listener) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.getResult() != null) {
                        listener.onSuccess();
                        writeUser(task.getResult().getUser(), token);
                    } else {
                        listener.onFail();
                    }
                });
    }

    /**
     * log out off firebase
     */
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

    public DatabaseReference getUserDatabaseRef() {
        return userDatabaseRef;
    }
}
