package com.coderschool.booketplace.utils;

import com.coderschool.booketplace.models.Book;

/**
 * Created by dattran on 11/18/16.
 */

public class Event {
    public static class ItemBookClick {
        Book book;
        public ItemBookClick(Book book) {
            this.book = book;
        }

        public Book getBook() {
            return book;
        }
    }

    public static class UserClick {
        String uid;

        public UserClick(String uid) {
            this.uid = uid;
        }

        public String getUser() {
            return uid;
        }
    }

    public static class UserItemClick {

    }

}
