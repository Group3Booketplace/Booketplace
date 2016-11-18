package com.coderschool.booketplace.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dattran on 11/18/16.
 */

public class FacebookUser {
    @SerializedName("birthday")
    String birthday;
    @SerializedName("gender")
    String gender;
    @SerializedName("location")
    FacebookLocation location;

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public FacebookLocation getLocation() {
        return location;
    }
}
