package com.coderschool.booketplace.models;

import com.coderschool.booketplace.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dattran on 11/27/16.
 */

public class Comment {
    private String text;
    private String user;
    private String time;
    private String uid;
    private String avatar;

    private static final String TEXT = "text";
    private static final String USER = "user";
    private static final String UID = "uid";
    private static final String TIME = "time";
    private static final String AVATAR = "avatar";
    public Comment() {}

    public Comment(String text, String user, String uid, String avatar) {
        this.text = text;
        this.user = user;
        this.time = DateUtils.getStringDate();
        this.uid = uid;
        this.avatar = avatar;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put(TEXT, text);
        result.put(USER, user);
        result.put(UID, uid);
        result.put(TIME, time);
        result.put(AVATAR, avatar);

        return result;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getUid() {
        return uid;
    }

    public String getAvatar() {
        return avatar;
    }
}
