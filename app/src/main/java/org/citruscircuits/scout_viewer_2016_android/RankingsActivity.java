package org.citruscircuits.scout_viewer_2016_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class RankingsActivity extends ViewerActivity {
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
        argumentsBundle.putString("field", Constants.DATA_TO_GRAPH.get(getIntent().getStringExtra("field")));
        argumentsBundle.putInt("team", getIntent().getIntExtra("team", 0));
        argumentsBundle.putBoolean("displayAsPercentage", getIntent().getBooleanExtra("displayAsPercentage", false));
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
    }

    public Fragment getFragment() {
        return null;
    };

}
