package com.coderschool.booketplace.models;

import android.os.Parcelable;

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
public class Book implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.isbn_10);
        dest.writeString(this.author);
        dest.writeString(this.condition);
        dest.writeString(this.createdDate);
        dest.writeString(this.description);
        dest.writeInt(this.discount);
        dest.writeString(this.modifiedDate);
        dest.writeString(this.name);
        dest.writeString(this.price);
        dest.writeValue(this.sell);
        dest.writeString(this.user);
        dest.writeList(this.images);
        dest.writeString(this.publishedDate);
        dest.writeString(this.publisher);
        dest.writeString(this.imageHeader);
        dest.writeString(this.key);
        dest.writeString(this.currency);
    }

    protected Book(android.os.Parcel in) {
        this.isbn_10 = in.readString();
        this.author = in.readString();
        this.condition = in.readString();
        this.createdDate = in.readString();
        this.description = in.readString();
        this.discount = in.readInt();
        this.modifiedDate = in.readString();
        this.name = in.readString();
        this.price = in.readString();
        this.sell = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.user = in.readString();
        this.images = new ArrayList<Image>();
        in.readList(this.images, Image.class.getClassLoader());
        this.publishedDate = in.readString();
        this.publisher = in.readString();
        this.imageHeader = in.readString();
        this.key = in.readString();
        this.currency = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(android.os.Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}


























