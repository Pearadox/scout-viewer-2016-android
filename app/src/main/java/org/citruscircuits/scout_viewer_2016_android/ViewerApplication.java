package org.citruscircuits.scout_viewer_2016_android;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class ViewerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
