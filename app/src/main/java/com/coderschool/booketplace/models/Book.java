package com.coderschool.booketplace.models;

import com.coderschool.booketplace.utils.DateUtils;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

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
    private Image image;
    private String publishedDate;
    private String publisher;
    private String imageHeader;
    private String key;
    private String category;
    private String location;

    // firebase key
    public static final String AUTHOR = "author";
    public static final String CONDITION = "condition";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_DATE = "createdDate";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String SELL = "sell";
    public static final String IMAGES_URL = "image";
    public static final String USER = "user";
    public static final String ISBN_10 = "isbn_10";
    public static final String DISCOUNT = "discount";
    public static final String MODIFIED_DATE = "modifiedDate";
    public static final String PUBLISHED_DATE = "publishDate";
    public static final String PUBLISHER = "publisher";
    public static final String KEY = "key";
    public static final String IMAGE_HEADER = "imageHeader";
    public static final String CATEGORY = "category";
    public static final String LOCATION = "location";

    // constructor for firebase
    public Book() {}

    // construct to create new book


    public Book(String name, String author, String category, String price, String currency, String description, String condition, String location, String user) {
        this.author = author;
        this.condition = condition;
        this.createdDate = DateUtils.getStringDate();
        this.description = description;
        this.name = name;
        this.price = price + " " + currency;
        this.sell = false;
        this.user = user;
        this.category = category;
        this.location = location;

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
        result.put(IMAGES_URL, image);
        result.put(KEY, key);
        result.put(MODIFIED_DATE, modifiedDate);
        result.put(PUBLISHED_DATE, publishedDate);
        result.put(PUBLISHER, publisher);
        result.put(ISBN_10, isbn_10);
        result.put(DISCOUNT, discount);
        result.put(IMAGE_HEADER, imageHeader);
        result.put(LOCATION, location);
        result.put(CATEGORY, category);

        return result;
    }

    ////

//    public long getISBN() {
//        return ISBN_10;
//    }

    public void addImage(Image image) {
        this.image = image;
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

    public Image getImage() {
        return image;
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

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }
}


























