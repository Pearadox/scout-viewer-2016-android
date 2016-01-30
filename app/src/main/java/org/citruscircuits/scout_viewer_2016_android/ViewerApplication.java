package org.citruscircuits.scout_viewer_2016_android;

import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.firebase.client.Firebase;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class ViewerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        FirebaseLists.teamsList = new FirebaseList<>(Constants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(Constants.TEAMS_UPDATED_ACTION));
            }
        }, Team.class);

        FirebaseLists.matchesList = new FirebaseList<>(Constants.MATCHES_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(Constants.MATCHES_UPDATED_ACTION));
            }
        }, Match.class);
    }
}
