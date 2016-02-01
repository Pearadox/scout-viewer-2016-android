package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.support.v4.app.Fragment;

import org.citruscircuits.scout_viewer_2016_android.RankingsActivity;

/**
 * Created by colinunger on 1/31/16.
 */
public class MatchRankingsActivity extends RankingsActivity {
    @Override
    public Fragment getFragment() {
        return new MatchRankingsActivityFragment();
    }
}
