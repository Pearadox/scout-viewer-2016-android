package org.citruscircuits.scout_viewer_2016_android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Competition;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;
import org.citruscircuits.scout_viewer_2016_android.services.PhotoSync;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class ViewerApplication extends Application {

    public static SharedPreferences sharedPreferences = null;
    public static Context appContext = null;

    @Override
    public void onCreate() {
        super.onCreate();


        new Instabug.Builder(this, "a81e83742330fc178964d1eb3554463f")
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventShake)
                .build();


        appContext = getApplicationContext();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

        startListListeners(getApplicationContext());




        restoreFromSharedPreferences();
        startService(new Intent(this, StarManager.class));
        startService(new Intent(this, PhotoSync.class));
    }


    public static void startListListeners(final Context context) {
        FirebaseLists.matchesList = new FirebaseList<>(Constants.MATCHES_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("MATCHES", key);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.MATCHES_UPDATED_ACTION).putExtra("key", key).putExtra("previousValue", previousValue));
            }
        }, Match.class);

        FirebaseLists.teamsList = new FirebaseList<>(Constants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("TEAMS", key);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.TEAMS_UPDATED_ACTION).putExtra("key", key));
            }
        }, Team.class);

        FirebaseLists.teamInMatchDataList = new FirebaseList<>(Constants.TEAM_IN_MATCH_DATAS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute(String key, String previousValue) {
                Log.i("TEAMS_IN_MATCHES", key);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Constants.TEAM_IN_MATCH_DATAS_UPDATED_ACTION).putExtra("key", key));
            }
        }, TeamInMatchData.class);
    }

    public static void setupFirebaseAuth(final Context context) {
        Firebase firebaseRef = new Firebase(Constants.ROOT_FIREBASE_PATH);

        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                // Do nothing if authenticated
                CharSequence text = "Authenticated!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.e("FireBase error", "Failed to auth");
            }
        };

        firebaseRef.authWithCustomToken(Constants.FIREBASE_KEYS.get(Constants.ROOT_FIREBASE_PATH), authResultHandler);
//        firebaseRef.authWithPassword("1678programming@gmail.com", "Squeezecrush1", authResultHandler);
    }


    public void restoreFromSharedPreferences() {
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        String jsonImportantMatchesAsString = sharedPreferences.getString("importantMatches", "[]");
        String jsonStarredTeamsAsString = sharedPreferences.getString("starredTeams", "[]");
        String jsonMatchesAddedByTeamAsString = sharedPreferences.getString("matchesAddedByTeam", "{}");

        try {
            StarManager.importantMatches = jsonArrayToIntegerList(new JSONArray(jsonImportantMatchesAsString));
            StarManager.starredTeams = jsonArrayToIntegerList(new JSONArray(jsonStarredTeamsAsString));
        } catch(JSONException jsone) {
            Log.e("test", "ERROR: " + jsone.getMessage());
        }

        try {
            JSONObject jsonMatchesAddedByTeam = new JSONObject(jsonMatchesAddedByTeamAsString);
            StarManager.matchesAddedByTeam = new HashMap<>();
            for (int i = 0; i < jsonMatchesAddedByTeam.length(); i++) {
                String key = (String)jsonMatchesAddedByTeam.names().get(i);
                Integer teamNumber = Integer.parseInt(key);
                List<Integer> matchesAdded = jsonArrayToIntegerList(jsonMatchesAddedByTeam.getJSONArray(key));
                StarManager.matchesAddedByTeam.put(teamNumber, matchesAdded);
            }
        } catch (JSONException jsone) {
            Log.e("test", "JSON ERROR: " + jsone.getMessage());
        }
    }

    public List<Integer> jsonArrayToIntegerList(JSONArray jsonArray) {
        List<Integer> list = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add((Integer)jsonArray.get(i));
            }
        } catch (JSONException jsone) {
            Log.e("test", "JSON ERROR: " + jsone.getMessage());
        }

        return list;
    }
}
