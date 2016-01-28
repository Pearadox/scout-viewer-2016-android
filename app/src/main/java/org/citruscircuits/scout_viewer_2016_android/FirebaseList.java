package org.citruscircuits.scout_viewer_2016_android;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by colinunger on 1/24/16.
 */
public class FirebaseList extends LinkedHashMap<String, Team> {

    public FirebaseList(String url, FirebaseUpdatedCallback firebaseUpdatedCallback) {
        setupFirebaseListening(url, firebaseUpdatedCallback);
    }

    public void setupFirebaseListening(String url, final FirebaseUpdatedCallback firebaseUpdatedCallback) {
        Firebase firebase = new Firebase(url);
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                remove(s);
                put(dataSnapshot.getKey(), dataSnapshot.getValue(Team.class));
                firebaseUpdatedCallback.execute();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                put(dataSnapshot.getKey(), dataSnapshot.getValue(Team.class));
                firebaseUpdatedCallback.execute();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                remove(dataSnapshot.getKey());
                firebaseUpdatedCallback.execute();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public interface FirebaseUpdatedCallback {
        void execute();
    }
}
