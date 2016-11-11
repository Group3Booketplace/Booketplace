package com.coderschool.booketplace.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.coderschool.booketplace.R;

/**
 * Created by DatTran on 11/11/16.
 */

public class BitmapUtils {

    public static Bitmap resize(Bitmap bitmap, Context context) {
        Resources resources = context.getResources();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, resources.getDimensionPixelSize(R.dimen.manga_dimen), resources.getDimensionPixelSize(R.dimen.manga_dimen), false);
        return resizedBitmap;
    }
}
