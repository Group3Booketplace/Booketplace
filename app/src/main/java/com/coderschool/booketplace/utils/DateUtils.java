package com.coderschool.booketplace.utils;

import java.text.ParseException;
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

    public static String getRelativeTimeAgo(String rawJsonDate) {
        if (rawJsonDate == null) return null;

        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = android.text.format.DateUtils.getRelativeTimeSpanString(
                    dateMillis,
                    System.currentTimeMillis(),
                    android.text.format.DateUtils.SECOND_IN_MILLIS)
                    .toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    public static String convertDateTime(String originTimeRaw) {
        if (originTimeRaw == null) return null;

        SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.getDefault());
        SimpleDateFormat resultFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        String dateString = "";
        Date date = null;
        try {
            date = originalFormat.parse(originTimeRaw);
            dateString = resultFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }
}
