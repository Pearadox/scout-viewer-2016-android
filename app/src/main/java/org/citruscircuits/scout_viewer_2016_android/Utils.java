package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class Utils {
    public static Object getObjectField(Object object, String fieldName) {
        try {
            String[] fieldNames = fieldName.split("\\.");
            Object modObject = object;
            for (String singleFieldLevelName : fieldNames) {
                if (modObject instanceof Map) {
                    LinkedHashMap lhm = (LinkedHashMap)modObject;
                    modObject = lhm.get(singleFieldLevelName);
                } else {
                    Field f = modObject.getClass().getDeclaredField(singleFieldLevelName);
                    f.setAccessible(true);

                    modObject = f.get(modObject);
                }
            }

            return modObject;
        } catch (Exception e) {
            Log.e("test", "The exception is " + e.getMessage());
            return null;
        }
    }

    public static Bitmap downloadImage(String urlString) {
        try {
            URL url = new URL("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
//            URLConnection uc = url.openConnection();
//            uc.addRequestProperty();
            Log.e("test", "Input stream is " + url.openStream().toString());
            InputStream in = new BufferedInputStream(url.openStream());
            Log.e("test", in.toString());
            Bitmap image = BitmapFactory.decodeStream(in);
            in.close();
            return image;
        } catch (MalformedURLException e) {
            Log.e("error", "Exception: " + e.getMessage());
            return null;
        } catch (IOException ioe) {
            Log.e("error", "io Exception: " + ioe.getMessage());
            return null;
        }
    }

    public static String getKeyForField(String field) {
        if (Constants.KEYS_TO_TITLES.containsKey(field)) {
            return Constants.KEYS_TO_TITLES.get(field);
        } else {
            return field;
        }
    }
}
