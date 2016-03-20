package org.citruscircuits.scout_viewer_2016_android;

import android.os.Bundle;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 1/24/16.
 */
public class FirebaseList<T> {
    private List<String> keys = new ArrayList<>();
    private List<T> values = new ArrayList<>();

    public FirebaseList(String url, FirebaseUpdatedCallback firebaseUpdatedCallback, Class<T> firebaseClass) {
        setupFirebaseListening(url, firebaseClass, firebaseUpdatedCallback);
    }

    public void setupFirebaseListening(String url, final Class<T> firebaseClass, final FirebaseUpdatedCallback firebaseUpdatedCallback) {
        final Firebase firebase = new Firebase(url);
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                T model = dataSnapshot.getValue(firebaseClass);
                String key = dataSnapshot.getKey();

                // Insert into the correct location, based on s
                if (s == null) {
                    values.add(0, model);
                    keys.add(0, key);
                } else {
                    int previousIndex = keys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == values.size()) {
                        values.add(model);
                        keys.add(key);
                    } else {
                        values.add(nextIndex, model);
                        keys.add(nextIndex, key);
                    }
                }

                firebaseUpdatedCallback.execute(key, null);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                T newModel = dataSnapshot.getValue(firebaseClass);
                int index = keys.indexOf(key);

                String previousValue;
                try {
                    previousValue = Utils.serializeClass(values.get(index)).toString();
                    Log.i("Previous Value", previousValue);
                } catch (JSONException | IllegalAccessException e) {
                    previousValue = null;
                    Log.i("Previous Value", "Failed to serialize");
                }
                values.set(index, newModel);

                firebaseUpdatedCallback.execute(key, previousValue);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = keys.indexOf(key);

                String previousValue;
                try {
                    previousValue = Utils.serializeClass(values.get(index)).toString();
                    Log.i("Previous Value", previousValue);
                } catch (JSONException | IllegalAccessException e) {
                    previousValue = null;
                    Log.i("Previous Value", "Failed to serialize");
                }

                keys.remove(index);
                values.remove(index);

                firebaseUpdatedCallback.execute(key, previousValue);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                T newModel = dataSnapshot.getValue(firebaseClass);
                int index = keys.indexOf(key);

                String previousValue;
                try {
                    previousValue = Utils.serializeClass(values.get(index)).toString();
                    Log.i("Previous Value", previousValue);
                } catch (JSONException | IllegalAccessException e) {
                    previousValue = null;
                    Log.i("Previous Value", "Failed to serialize");
                }


                values.remove(index);
                keys.remove(index);
                if (s == null) {
                    values.add(0, newModel);
                    keys.add(0, key);
                } else {
                    int previousIndex = keys.indexOf(s);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == values.size()) {
                        values.add(newModel);
                        keys.add(key);
                    } else {
                        values.add(nextIndex, newModel);
                        keys.add(nextIndex, key);
                    }
                }

                firebaseUpdatedCallback.execute(key, previousValue);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Firebase List", "Listen was cancelled, no more updates will occur");
            }
        });
    }

    public interface FirebaseUpdatedCallback {
        void execute(String key, String previousValue);
    }

    public T getFirebaseObjectByKey(String key) {
        return values.get(keys.indexOf(key));
    }

    public List<String> getKeys() {
        return keys;
    }

    public List<T> getValues() {
        return values;
    }
}
