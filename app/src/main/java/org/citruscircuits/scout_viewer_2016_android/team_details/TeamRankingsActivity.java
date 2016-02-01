package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.support.v4.app.Fragment;

import org.citruscircuits.scout_viewer_2016_android.RankingsActivity;

/**
 * Created by colinunger on 1/31/16.
 */
public class TeamRankingsActivity extends RankingsActivity {

    @Override
    public Fragment getFragment() {
        return new TeamRankingsActivityFragment();
    }
}
