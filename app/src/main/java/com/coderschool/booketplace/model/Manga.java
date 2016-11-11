package com.coderschool.booketplace.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.PropertyName;

/**
 * Created by DatTran on 11/11/16.
 */

public class Manga {
    @PropertyName("name")
    private String name;

    @PropertyName("author")
    private String author;

    @PropertyName("price")
    private String price;

    @PropertyName("description")
    private String description;

    @PropertyName("condition")
    private String condition;

    @PropertyName("time")
    private long time;

    @PropertyName("location")
    private Location location;

    public Manga(String name, String author, String price, String description, String condition, long time, LatLng latLng) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.condition = condition;
        this.time = time;
        this.location = new Location(latLng);
    }
}
