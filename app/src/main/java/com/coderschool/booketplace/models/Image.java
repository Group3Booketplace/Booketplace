package com.coderschool.booketplace.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import org.parceler.Parcel;

/**
 * Created by dattran on 11/16/16.
 */

@IgnoreExtraProperties
@Parcel
public class Image {
//    private String url;
    @PropertyName("width")
    private int width;
    @PropertyName("height")
    private int height;
    @PropertyName("url")
    private String url;
    @PropertyName("color")
    private int color;

    public Image() {}

    public Image(int width, int height, String url, int color) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColor() {
        return color;
    }
}
