package com.coderschool.booketplace.models;

import com.coderschool.booketplace.utils.DateUtils;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinh on 11/15/16.
 */

@IgnoreExtraProperties
@Parcel
public class Book {

    // property
    private String isbn_10;
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
    private String currency;

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
    public static final String ISBN_10 = "isbn_10";
    public static final String DISCOUNT = "discount";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String PUBLISHED_DATE = "publishDate";
    public static final String PUBLISHER = "publisher";
    public static final String KEY = "key";
    public static final String IMAGE_HEADER = "imageHeader";


    // constructor for firebase
    public Book() {}

    // construct to create new book


    public Book(String author, String condition, String description, String name, String price, String currency, String user) {
        this.author = author;
        this.condition = condition.toLowerCase();
        this.createdDate = DateUtils.getStringDate();
        this.description = description;
        this.name = name;
        this.price = price + " " + currency;
        this.sell = false;
        this.images = new ArrayList<>();
        this.user = user;
        // mock data
        this.publisher = "NXB Tre";
        this.discount = 99;
        this.publishedDate = "Wed Nov 16 13:17:12 +07:00 2016";
        this.imageHeader = "https://nothing.in.here";
        this.modifiedDate = "Wed Nov 16 13:17:12 +07:00 2016";
        this.isbn_10 = "0439708184";
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
        result.put(KEY, key);
        result.put(MODIFIED_DATE, modifiedDate);
        result.put(PUBLISHED_DATE, publishedDate);
        result.put(PUBLISHER, publisher);
        result.put(ISBN_10, isbn_10);
        result.put(DISCOUNT, discount);
        result.put(IMAGE_HEADER, imageHeader);

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

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public int getDiscount() {
        return discount;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Boolean getSell() {
        return sell;
    }

    public String getUser() {
        return user;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageHeader() {
        return imageHeader;
    }
}


























