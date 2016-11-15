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
    private ArrayList<Book> ownerBook;
    private ArrayList<Book> subscribedBook;
    private ArrayList<User> following;
    private ArrayList<User> follower;
    private ArrayList<Message> messages;

    /// more method add new here!
    ///
    ///






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

    public ArrayList<Book> getOwnerBook() {
        return ownerBook;
    }

    public ArrayList<Book> getSubscribedBook() {
        return subscribedBook;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public ArrayList<User> getFollower() {
        return follower;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
