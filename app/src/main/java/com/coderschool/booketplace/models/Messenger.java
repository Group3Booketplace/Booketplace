package com.coderschool.booketplace.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinh on 11/15/16.
 */

public class Messenger {
    private User user;
    private String uid;
    private String date;
    private String lastMessage;

    public Messenger() {

    }

    public Messenger(String uid, String date, String lastMessage) {
        this.uid = uid;
        this.date = date;
        this.lastMessage = lastMessage;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("lastMessage", lastMessage);
        result.put("date", date);

        return result;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUid(String userUid) {
        this.uid = userUid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public User getUser() {
        return user;
    }

    public String getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
