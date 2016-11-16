package com.coderschool.booketplace.models;

import com.coderschool.booketplace.utils.DateUtils;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinh on 11/15/16.
 */

@IgnoreExtraProperties
public class Book {

    // property
//    private long ISBN;
    private String author;
    private String condition;
    private String createdDate;
    private String description;
//    private int discount;
//    private ArrayList<String> images;
//    private Date modifiedDate;
    private String name;
    private String price;
    private Boolean sell;
    private String user;
    private String images;
//    private Date publishedDate;
//    private String publisher;
//    private String imageHeader;

    /// more method add new here!
    ///
    ///

    // firebase key
    public static final String AUTHOR = "author";
    public static final String CONDITION = "condition";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_DATE = "createdDate";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String SELL = "sell";
    public static final String IMAGES_URL = "images";
    public static final String USER = "user";


    // constructor for firebase
    public Book() {}

    // construct to create new book


    public Book(String author, String condition, String description, String name, String price) {
        this.author = author;
        this.condition = condition.toLowerCase();
        this.createdDate = DateUtils.getStringDate();
        this.description = description;
        this.name = name;
        this.price = price;
        this.sell = false;
    }

    // write to database
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(AUTHOR, author);
        result.put(CONDITION, condition);
        result.put(DESCRIPTION, description);
        result.put(CREATED_DATE, createdDate);
        result.put(NAME, name);
        result.put(PRICE, price);
        result.put(SELL, sell);
        result.put(USER, user);
        result.put(IMAGES_URL, images);

        return result;
    }

    ////

//    public long getISBN() {
//        return ISBN;
//    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAuthor() {
        return author;
    }

    public String getCondition() {
        return condition;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

//    public int getDiscount() {
//        return discount;
//    }

//    public ArrayList<String> getImages() {
//        return images;
//    }

//    public Date getModifiedDate() {
//        return modifiedDate;
//    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

//    public Date getPublishedDate() {
//        return publishedDate;
//    }

//    public String getPublisher() {
//        return publisher;
//    }

//    public String getImageHeader() {
//        return imageHeader;
//    }
}


























