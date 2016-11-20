package com.coderschool.booketplace.models;

/**
 * Created by vinh on 11/15/16.
 */
public class MessageItem {

    private String uid;
    private String content;
    private String date;
    private User user;

    public MessageItem() {

    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
