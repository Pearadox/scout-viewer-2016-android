package org.citruscircuits.scout_viewer_2016_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Settings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");

        final Spinner spinner = (Spinner)findViewById(R.id.settingsFirebaseSpinner);
        final List<String> firebaseKeys = new ArrayList<>(Constants.FIREBASE_KEYS.keySet());
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, firebaseKeys);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Activity context = this;
        spinner.setSelection(adapter.getPosition(Constants.ROOT_FIREBASE_PATH));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("AUTH", "HERE1");
                if (!adapter.getItem(position).equals(Constants.ROOT_FIREBASE_PATH)) {
                    new AlertDialog.Builder(context)
                            .setTitle("Change Database")
                            .setMessage("Are you sure you want to change the database to view?\nNOTE: Some databases WILL cause the app to crash!")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i("AUTH", "HERE2");
                                    //cancel previous listeners
                                    FirebaseLists.matchesList.cancelListen();
                                    FirebaseLists.teamInMatchDataList.cancelListen();
                                    FirebaseLists.teamsList.cancelListen();
                                    //set new constants
                                    Constants.ROOT_FIREBASE_PATH = adapter.getItem(position);
                                    Constants.MATCHES_PATH = Constants.ROOT_FIREBASE_PATH + "Matches";
                                    Constants.TEAMS_PATH = Constants.ROOT_FIREBASE_PATH + "Teams";
                                    Constants.TEAM_IN_MATCH_DATAS_PATH = Constants.ROOT_FIREBASE_PATH + "TeamInMatchDatas";
                                    //start new lists
                                    ViewerApplication.startListListeners(context);
                                    ViewerApplication.setupFirebaseAuth(context);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    spinner.setSelection(firebaseKeys.indexOf(Constants.ROOT_FIREBASE_PATH));
                                }
                            })
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
