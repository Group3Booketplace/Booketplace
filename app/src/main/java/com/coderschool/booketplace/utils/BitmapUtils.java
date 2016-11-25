package com.coderschool.booketplace.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.coderschool.booketplace.R;
import com.coderschool.booketplace.models.Image;

import java.io.ByteArrayOutputStream;

/**
 * Created by DatTran on 11/11/16.
 */

public class BitmapUtils {

    public static Bitmap resize(Bitmap bitmap, Context context) {
        Resources resources = context.getResources();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, resources.getDimensionPixelSize(R.dimen.manga_dimen), resources.getDimensionPixelSize(R.dimen.manga_dimen), false);
        return resizedBitmap;
    }

    /**
     * resize bitmap lossless
     * @param bitmap to resize
     * @param factor resize factor
     * @return resized bitmap
     */
    public static Bitmap resize(Bitmap bitmap, float factor) {
        Matrix matrix = new Matrix();
        matrix.postScale(factor, factor);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    /**
     * Convert a bitmap to a stream of byte
     * @param bitmap
     * @return stream of byte
     */
    public static byte[] steamFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static Drawable placeholder(Context context, Image image, int frameWidth) {
        Bitmap bi = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        bi.setPixel(0, 0, image.getColor());
        int frameHeight = frameWidth * image.getHeight() / image.getWidth();
        Bitmap placeholder = Bitmap.createScaledBitmap(bi, frameWidth, frameHeight, false);
        return new BitmapDrawable(context.getResources(), placeholder);
    }

}
