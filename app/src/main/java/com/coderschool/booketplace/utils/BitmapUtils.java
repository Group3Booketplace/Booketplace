package com.coderschool.booketplace.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.coderschool.booketplace.R;

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

}
