package com.coderschool.booketplace.models;

import android.os.Parcel;
import android.os.Parcelable;

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
public class Book implements Parcelable {

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
    private Image image;
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
        result.put(IMAGES_URL, image);

        return result;
    }

    ////

//    public long getISBN() {
//        return ISBN;
//    }


    public void setUser(String user) {
        this.user = user;
    }

    public void setImages(Image image) {
        this.image = image;
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

    public Image getImages() {
        return image;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.condition);
        dest.writeString(this.createdDate);
        dest.writeString(this.description);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeValue(this.sell);
        dest.writeString(this.user);
        dest.writeParcelable(this.image, flags);
    }

    protected Book(Parcel in) {
        this.author = in.readString();
        this.condition = in.readString();
        this.createdDate = in.readString();
        this.description = in.readString();
        this.name = in.readString();
        this.price = in.readString();
        this.sell = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.user = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}


























