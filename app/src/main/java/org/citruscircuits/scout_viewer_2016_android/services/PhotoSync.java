package org.citruscircuits.scout_viewer_2016_android.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by colinunger on 2/6/16.
 */
public class PhotoSync extends Service {
    private Map<Integer, String> teamImageURLs = new HashMap<>();
    private Map<Integer, String> completedTeamImageURLs = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test", "Starting photos listener");

        SharedPreferences  sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        String teamImageURLsAsString = sharedPreferences.getString("teamImageURLs", "{0 : \"\"}");
        try {
            JSONObject urls = new JSONObject(teamImageURLsAsString);
            for (int i = 0; i < urls.names().length(); i++) {
                String key = (String)urls.names().get(i);
                completedTeamImageURLs.put(Integer.parseInt(key), urls.getString(key));
                teamImageURLs.put(Integer.parseInt(key), urls.getString(key));
            }
        } catch (JSONException jsone) {
            Log.e("test", "JSON Exception: " + jsone.getMessage());
        }

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                for (String teamNumberString : FirebaseLists.teamsList.getKeys()) {
                    Integer teamNumber = Integer.parseInt(teamNumberString);
                    String selectedImageURLString = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString()).selectedImageUrl;
                    if (!selectedImageURLString.equals(teamImageURLs.get(teamNumber))) {
                        teamImageURLs.put(teamNumber, selectedImageURLString);
                        PhotoAsyncTask photoAsyncTask = new PhotoAsyncTask();
                        photoAsyncTask.execute(teamNumber, selectedImageURLString);
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

    public void saveTeamImage(Context context, Integer teamNumber, Bitmap bitmap, String selectedImageURLString) {
        Log.e("test", "Saving image for team " + teamNumber.toString());
        File file = new File(context.getFilesDir(), "image_" + teamNumber.toString());
        saveBitmapToFile(file, bitmap);

        completedTeamImageURLs.put(teamNumber, selectedImageURLString);
        saveToSharedPreferences();
        Intent photoBroadcastIntent = new Intent(Constants.NEW_TEAM_PHOTO_ACTION);
        photoBroadcastIntent.putExtra("teamNumber", teamNumber);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(photoBroadcastIntent);
    }

    private void saveBitmapToFile(File file, Bitmap bitmap) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException ioe) {
            Log.e("test", "ERROR: " + ioe.getMessage());
        }
    }

    private class PhotoAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Log.e("test", "Starting thread for team " + params[0].toString());
                String selectedImageURLString = (String)params[1];
                URL url = new URL(selectedImageURLString);
//                URL url = new URL("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
                InputStream in = new BufferedInputStream(url.openStream());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Bitmap image = BitmapFactory.decodeStream(in, new Rect(0, 0, 0, 0), options);
                in.close();
                saveTeamImage(getApplicationContext(), (Integer)params[0], image, selectedImageURLString);
            } catch (MalformedURLException e) {
                Log.e("error", "Exception: " + e.getMessage());
                deleteTeamImage(getApplicationContext(), (Integer)params[0]);
            } catch (IOException ioe) {
                Log.e("error", "IO Exception: " + ioe.getMessage());
            }

            return null;
        }
    }

    public void saveToSharedPreferences() {
        Map<String, String> savableTeamImageURLs = integerKeyMapToStringKeyMap(completedTeamImageURLs);
        JSONObject teamImageURLsAsJSONObject = new JSONObject(savableTeamImageURLs);
        getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE).edit().putString("teamImageURLs", teamImageURLsAsJSONObject.toString()).commit();
    }

    public Map<String, String> integerKeyMapToStringKeyMap(Map<Integer, String> integerKeyMap) {
        Map<String, String> stringKeyMap = new HashMap<>();
        for (Integer key : integerKeyMap.keySet()) {
            stringKeyMap.put(key.toString(), integerKeyMap.get(key));
        }

        return stringKeyMap;
    }

    public void deleteTeamImage(Context context, Integer teamNumber) {
        File file = new File(context.getFilesDir(), "image_" + teamNumber.toString());
        Log.e("test", "The file deleted: " + file.delete());
    }

}
