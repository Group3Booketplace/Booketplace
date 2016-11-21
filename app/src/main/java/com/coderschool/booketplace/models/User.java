package com.coderschool.booketplace.models;

import com.coderschool.booketplace.api.FacebookUser;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinh on 11/15/16.
 */


public class User {
    private String address;
    private String avatar;
    private String birthday;
    private String email;
    private String gender;
    private String name;
    private String phone;
    private float ratingOverall;
    private String location;
    private String uid;
    private ArrayList<Rating> ratings;
    private ArrayList<Book> ownerBooks;
    private ArrayList<Book> subscribedBooks;
    private ArrayList<User> followings;
    private ArrayList<User> followers;
    private ArrayList<Message> messages;

    public static User createExampleMessage() {
        User user = new User();
        user.avatar = "http://a.vnexpress.net/avatar/c60x60/6/51/4/8439423672_1422255146.jpg";
        user.name = "Anonymous";
        return user;
    }

    // firebase key
    public static final String ADDRESS = "address";
    public static final String AVATAR = "avatar";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String RATING_OVERALL = "ratingOverall";
    public static final String RATINGS = "ratings";
    public static final String OWNER_BOOKS = "ownerBooks";
    public static final String SUBSCRIBED_BOOK = "subscribedBooks";
    public static final String FOLLOWINGS = "followings";
    public static final String FOLLOWERS = "followers";
    public static final String MESSAGES = "messages";
    public static final String LOCATION = "location";
    public static final String UID = "uid";

    // constructor for firebase
    public User() {}

    // own constructor

    public User(FirebaseUser user) {
        this.avatar = user.getPhotoUrl().toString();
        this.email = user.getEmail();
        this.name = user.getDisplayName();
        this.ratingOverall = 0;
        this.ownerBooks = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.subscribedBooks = new ArrayList<>();
        this.messages = new ArrayList<>();

        // add more
        this.uid = user.getUid();
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put(AVATAR, avatar);
        result.put(EMAIL, email);
        result.put(NAME, name);
        result.put(BIRTHDAY, birthday);
        result.put(ADDRESS, address);
        result.put(GENDER, gender);
        result.put(PHONE, phone);
        result.put(RATING_OVERALL, ratingOverall);
        result.put(RATINGS, ratings);
        result.put(OWNER_BOOKS, ownerBooks);
        result.put(FOLLOWERS, followers);
        result.put(FOLLOWINGS, followings);
        result.put(SUBSCRIBED_BOOK, subscribedBooks);
        result.put(MESSAGES, messages);
        result.put(LOCATION, location);

        // add more
        result.put(UID, uid);

        return result;
    }

    /**
     * setter
     */

    public void setUser(FacebookUser user) {
        this.birthday = user.getBirthday();
        this.gender = user.getGender();
//        this.location = user.getLocation().getName();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getter
     */

    public String getAddress() {
        return address;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public float getRatingOverall() {
        return ratingOverall;
    }

    public String getUid() {
        return uid;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public ArrayList<Book> getOwnerBooks() {
        return ownerBooks;
    }

    public ArrayList<Book> getSubscribedBooks() {
        return subscribedBooks;
    }

    public ArrayList<User> getFollowings() {
        return followings;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public String getLocation() {
        return location;
    }
}
