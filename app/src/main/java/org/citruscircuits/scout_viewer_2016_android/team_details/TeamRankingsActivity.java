package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;

import org.citruscircuits.scout_viewer_2016_android.R;

public class TeamRankingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_rankings);
        Fragment fragment = getFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.teamRankingsActivityRelativeLayout, fragment, "").commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rankings, menu);
        return true;
    }


    public Fragment getFragment() {
        Fragment fragment = new TeamRankingsActivityFragment();
        Bundle arguments = new Bundle();
        arguments.putString("field", getIntent().getStringExtra("field"));
        arguments.putInt("team", getIntent().getIntExtra("team", 0));
        arguments.putBoolean("displayValueAsPercentage", getIntent().getBooleanExtra("displayValueAsPercentage", false));
        fragment.setArguments(arguments);
        return fragment;
    }
}
