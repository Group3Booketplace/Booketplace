package com.coderschool.booketplace.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dattran on 11/16/16.
 */

public class DateUtils {

    public static String getStringDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
        dateFormat.setLenient(true);
        return dateFormat.format(date);
    }
}
