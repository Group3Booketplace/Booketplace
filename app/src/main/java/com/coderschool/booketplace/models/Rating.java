package com.coderschool.booketplace.models;

import java.util.Date;

/**
 * Created by vinh on 11/15/16.
 */
public class Rating {
    private User user;
    private String userUid;
    private String comment;
    private Date date;
    private double star;

    /// more method add new here!
    ///
    ///






    ////


    public User getUser() {
        return user;
    }

    public String getUserUid() {
        return userUid;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public double getStar() {
        return star;
    }
}
