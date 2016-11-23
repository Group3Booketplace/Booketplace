package com.coderschool.booketplace.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinh on 11/15/16.
 */
public class Chat {

    private static String UID = "uid";
    private static String CONTENT = "content";
    private static String DATE = "date";

    private String uid;
    private String content;
    private String date;
    private User user;

    public Chat() {

    }

    public Chat(String uid, String content, String date) {
        this.uid = uid;
        this.content = content;
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(UID, uid);
        result.put(CONTENT, content);
        result.put(DATE, date);

        return result;
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
