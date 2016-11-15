package com.coderschool.booketplace.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by vinh on 11/15/16.
 */

public class Book {

    private long ISBN;
    private String author;
    private String condition;
    private Date createdDate;
    private String description;
    private int discount;
    private ArrayList<String> images;
    private Date modifiedDate;
    private String name;
    private double price;
    private Date publishedDate;
    private String publisher;
    private String imageHeader;

    /// more method add new here!
    ///
    ///






    ////

    public long getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getCondition() {
        return condition;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public int getDiscount() {
        return discount;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getImageHeader() {
        return imageHeader;
    }
}


























