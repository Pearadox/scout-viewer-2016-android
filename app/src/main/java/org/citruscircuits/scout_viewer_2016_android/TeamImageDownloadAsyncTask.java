package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by colinunger on 1/26/16.
 */
public class TeamImageDownloadAsyncTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        return Utils.downloadImage("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        Bitmap bitmap = (Bitmap)o;

    }
}
