package com.coderschool.booketplace.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

/**
 * Created by dattran on 11/16/16.
 */

@IgnoreExtraProperties
public class Image {
//    private String url;
    @PropertyName("width")
    private int width;
    @PropertyName("height")
    private int height;

    public Image() {}

    public Image(int width, int height) {
//        this.url = url;
        this.width = width;
        this.height = height;
    }

//    public String getUrl() {
//        return url;
//    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
