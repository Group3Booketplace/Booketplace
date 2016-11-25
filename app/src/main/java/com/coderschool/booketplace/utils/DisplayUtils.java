package com.coderschool.booketplace.utils;

import com.coderschool.booketplace.models.Image;

/**
 * Created by dattran on 11/16/16.
 */

public class DisplayUtils {
    public static int getImageHeight(int width, Image image) {
        return width * image.getHeight() / image.getWidth();

    }
}
