package org.citruscircuits.scout_viewer_2016_android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.firebase.client.Firebase;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Competition;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;
import org.citruscircuits.scout_viewer_2016_android.services.PhotoSync;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class ViewerApplication extends Application {

//    PhotoSync photoSync;
    Integer lastMatch = -1;
    private static List<Integer> starredMatches = new ArrayList<>();
    private static SharedPreferences sharedPreferences = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        String jsonStarredMatchesAsString = sharedPreferences.getString("starredMatches", "[]");
        try {
            JSONArray jsonStarredMatches = new JSONArray(jsonStarredMatchesAsString);
            for (int i = 0; i < jsonStarredMatches.length(); i++) {
                Log.e("test", "Adding match " + jsonStarredMatches.get(i).toString() + " to starred matches");
                starredMatches.add((Integer) jsonStarredMatches.get(i));
            }
        } catch (JSONException jsone) {
            Log.e("test", "JSON ERROR: " + jsone.getMessage());
        }

//        new FirebaseList<>("https://1678-dev3-2016.firebaseio.com/", new FirebaseList.FirebaseUpdatedCallback() {
//            @Override
//            public void execute() {
//                Log.e("test", "GOT THE COMPETITION!");
//            }
//        }, Competition.class);

        FirebaseLists.matchesList = new FirebaseList<>(Constants.MATCHES_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(Constants.MATCHES_UPDATED_ACTION));
            }
        }, Match.class);

        FirebaseLists.teamsList = new FirebaseList<>(Constants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(Constants.TEAMS_UPDATED_ACTION));
            }
        }, Team.class);

        FirebaseLists.teamInMatchDataList = new FirebaseList<>(Constants.TEAM_IN_MATCH_DATAS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(Constants.TEAM_IN_MATCH_DATAS_UPDATED_ACTION));
            }
        }, TeamInMatchData.class);




        startService(new Intent(this, StarManager.class));
        startService(new Intent(this, PhotoSync.class));
    }

    public static void addStarredMatch(Integer matchNumber) {
        if (!starredMatches.contains(matchNumber)) {
            starredMatches.add(matchNumber);
            Collections.sort(starredMatches);
            JSONArray jsonObject = new JSONArray(starredMatches);
            sharedPreferences.edit().putString("starredMatches", jsonObject.toString()).commit();
        }
    }

    public static void removeStarredMatch(Integer matchNumber) {
        if (starredMatches.contains(matchNumber)) {
            starredMatches.remove(matchNumber);
            Collections.sort(starredMatches);
            JSONArray jsonObject = new JSONArray(starredMatches);
            sharedPreferences.edit().putString("starredMatches", jsonObject.toString()).commit();
        }
    }

    public static boolean isStarredMatch(Integer matchNumber) {
        return starredMatches.contains(matchNumber);
    }

    public static List<Integer> getStarredMatches() {
        return new ArrayList<>(starredMatches);
    }
}
