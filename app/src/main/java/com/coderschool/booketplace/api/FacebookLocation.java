package com.coderschool.booketplace.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dattran on 11/18/16.
 */

public class FacebookLocation {
    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }
}
