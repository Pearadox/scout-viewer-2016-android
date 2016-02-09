package org.citruscircuits.scout_viewer_2016_android.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by colinunger on 2/6/16.
 */
public class PhotoSync extends Service {
    private Map<Integer, String> teamImageURLs = new HashMap<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test", "Starting photos listener");

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                for (String teamNumberString : FirebaseLists.teamsList.getKeys()) {
                    Integer teamNumber = Integer.parseInt(teamNumberString);
                    String selectedImageURLString = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString()).selectedImageUrl;
                    if (teamImageURLs.get(teamNumber) != selectedImageURLString) {
                        PhotoAsyncTask photoAsyncTask = new PhotoAsyncTask();
                        photoAsyncTask.execute(teamNumber);
//                        downloadImageForTeam(teamNumber);
//                        teamImageURLs.put(teamNumber, selectedImageURLString);
                    }
                }
            }
        }, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void downloadImageForTeam(final Integer teamNumber) {
        final String urlString = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString()).selectedImageUrl;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("test", "Starting thread for team " + teamNumber.toString());
//                URL url = new URL(urlString);
                    URL url = new URL("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
                    InputStream in = new BufferedInputStream(url.openStream());
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 3;
                    Bitmap image = BitmapFactory.decodeStream(in, new Rect(0, 0, 0, 0), options);
                    in.close();
                    saveTeamImage(getApplicationContext(), teamNumber, image);
                } catch (MalformedURLException e) {
                    Log.e("error", "Exception: " + e.getMessage());
                } catch (IOException ioe) {
                    Log.e("error", "IO Exception: " + ioe.getMessage());
                }
            }
        }).start();
    }

    public void saveTeamImage(Context context, Integer teamNumber, Bitmap bitmap) {
        Log.e("test", "Saving image for team " + teamNumber.toString());
        File file = new File(context.getFilesDir(), "image_" + teamNumber.toString());
        saveBitmapToFile(file, bitmap);

        Intent photoBroadcastIntent = new Intent(Constants.NEW_TEAM_PHOTO_ACTION);
        photoBroadcastIntent.putExtra("teamNumber", teamNumber);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(photoBroadcastIntent);
    }

    private void saveBitmapToFile(File file, Bitmap bitmap) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (IOException ioe) {
            Log.e("test", "ERROR: " + ioe.getMessage());
        }
    }

    private class PhotoAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Log.e("test", "Starting thread for team " + params[0].toString());
//                URL url = new URL(urlString);
                URL url = new URL("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
                InputStream in = new BufferedInputStream(url.openStream());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Bitmap image = BitmapFactory.decodeStream(in, new Rect(0, 0, 0, 0), options);
                in.close();
                saveTeamImage(getApplicationContext(), (Integer)params[0], image);
            } catch (MalformedURLException e) {
                Log.e("error", "Exception: " + e.getMessage());
            } catch (IOException ioe) {
                Log.e("error", "IO Exception: " + ioe.getMessage());
            }

            return null;
        }
    }
}
