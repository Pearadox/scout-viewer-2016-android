package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.RankingsActivity;

/**
 * Created by colinunger on 2/13/16.
 */
public class TeamInMatchDataRankingsActivity extends RankingsActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rankings, menu);
        return true;
    }

    @Override
    public Fragment getFragment() {
        Fragment fragment = new TeamInMatchDataRankingsFragment();
        Bundle arguments = new Bundle();
        arguments.putString("field", getIntent().getStringExtra("field"));
        fragment.setArguments(arguments);
        return fragment;
    }
}
