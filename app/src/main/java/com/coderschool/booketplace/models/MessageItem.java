package com.coderschool.booketplace.models;

import java.util.Date;

/**
 * Created by vinh on 11/15/16.
 */
public class MessageItem {
    private String key;
    private Date date;
    private String message;
    private String username;
    private String avatar;
    private boolean isOwner;
    /// more method add new here!
    ///
    ///


    public static MessageItem createExampleOwnChat() {
        MessageItem messageItem = new MessageItem();
        messageItem.date = new Date();
        messageItem.message = "Hello world";
        messageItem.avatar = "https://stmy.vnexpress.net/myvne/i/v1/graphics/img_60x60.gif";
        messageItem.isOwner = true;
        return messageItem;
    }

    public static MessageItem createExampleFriendChat() {
        MessageItem messageItem = new MessageItem();
        messageItem.date = new Date();
        messageItem.message = "Hello world";
        messageItem.avatar = "https://stmy.vnexpress.net/myvne/i/v1/graphics/img_60x60.gif";
        messageItem.isOwner = false;
        return messageItem;
    }


    ////


    public String getKey() {
        return key;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isOwner() {
        return isOwner;
    }
}
