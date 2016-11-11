package com.coderschool.booketplace.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.coderschool.booketplace.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DatTran on 11/11/16.
 */

public class FileUtils {

    public static File createPhotoFile(Context context) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), context.getString(R.string.app_name));
        return new File(storageDir.getPath() + File.separator + imageFileName);
    }

    public static void store(Bitmap bitmap, String path) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File resizeFile = new File(path);
        resizeFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(resizeFile);
        fos.write(bytes.toByteArray());
        fos.close();
    }
}
