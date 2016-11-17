package com.coderschool.booketplace.models;

import com.coderschool.booketplace.utils.DateUtils;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinh on 11/15/16.
 */

@IgnoreExtraProperties
public class Book {

    // property
    private String ISBN_10;
    private String author;
    private String condition;
    private String createdDate;
    private String description;
    private int discount;
    private String modifiedDate;
    private String name;
    private String price;
    private Boolean sell;
    private String user;
    private ArrayList<Image> images;
    private String publishedDate;
    private String publisher;
    private String imageHeader;
    private String key;

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


    public Book(String author, String condition, String description, String name, String price, String user) {
        this.author = author;
        this.condition = condition.toLowerCase();
        this.createdDate = DateUtils.getStringDate();
        this.description = description;
        this.name = name;
        this.price = price;
        this.sell = false;
        this.images = new ArrayList<>();
        this.user = user;
        this.publisher = "NXB Tre";
        this.publishedDate = "Wed Nov 16 13:17:12 +07:00 2016";
        this.imageHeader = "https://nothing.in.here";
        this.modifiedDate = "Wed Nov 16 13:17:12 +07:00 2016";
        this.ISBN_10 = "0439708184";
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
//        return ISBN_10;
//    }

    public void addImage(Image image) {
        this.images.add(image);
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
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

    public ArrayList<Image> getImages() {
        return images;
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


























