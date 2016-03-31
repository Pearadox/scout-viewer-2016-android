package org.citruscircuits.scout_viewer_2016_android;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

        Spinner spinner = (Spinner)findViewById(R.id.settingsFirebaseSpinner);
        List<String> fireBaseKeys = new ArrayList<>(Constants.FIREBASE_KEYS.keySet());
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fireBaseKeys);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final Activity context = this;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.ROOT_FIREBASE_PATH = adapter.getItem(position);
                Constants.MATCHES_PATH = Constants.ROOT_FIREBASE_PATH + "Matches";
                Constants.TEAMS_PATH = Constants.ROOT_FIREBASE_PATH + "Teams";
                Constants.TEAM_IN_MATCH_DATAS_PATH = Constants.ROOT_FIREBASE_PATH + "TeamInMatchDatas";
                ViewerApplication.startListListeners(context);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinner.setSelection(adapter.getPosition(Constants.ROOT_FIREBASE_PATH));
    }
}
