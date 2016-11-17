package com.coderschool.booketplace.models;

import java.util.ArrayList;

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
    private double ratingOverall;
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

    // constructor for firebase
    public User() {}

    // own constructor

    public User(String avatar, String email, String name) {
        this.avatar = avatar;
        this.email = email;
        this.name = name;
    }

    ////

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

    public double getRatingOverall() {
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
}
