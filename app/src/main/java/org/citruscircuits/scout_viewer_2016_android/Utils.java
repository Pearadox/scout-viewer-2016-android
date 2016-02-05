package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
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

    public static int getRankOfObject(Object o, List<Object> os, String fieldname) {
        Collections.sort(os, new ObjectFieldComparator(fieldname, true));
        return os.indexOf(o);
    }

    public static Bitmap downloadImage(String urlString) {
        try {
            URL url = new URL("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
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
}
