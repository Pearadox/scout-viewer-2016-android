package org.citruscircuits.scout_viewer_2016_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public abstract class RankingsActivity extends ActionBarActivity {
    private boolean isShowingGraph;

    private Integer teamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        String field = getIntent().getStringExtra("field");
        setTitle(Constants.KEYS_TO_TITLES.get(field));

        Fragment fragment = new TeamInMatchDataGraphFragment();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString("field", getIntent().getStringExtra("field"));
        argumentsBundle.putInt("team", getIntent().getIntExtra("team", 0));
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
    }

    public abstract Fragment getFragment();

}
