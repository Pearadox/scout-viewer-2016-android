package org.citruscircuits.scout_viewer_2016_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.TeamScheduleFragment;

/**
 * Created by colinunger on 2/14/16.
 */
public class MatchesActivity extends ViewerActivity {
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_rankings);
        String field = getIntent().getStringExtra("field");
        setTitle(Constants.KEYS_TO_TITLES.get(field));

        Fragment fragment = new TeamScheduleFragment();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putInt("teamNumber", getIntent().getIntExtra("teamNumber", 0));
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
    }
}
