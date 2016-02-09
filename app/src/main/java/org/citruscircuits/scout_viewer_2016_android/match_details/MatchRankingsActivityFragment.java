package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsFragment;

/**
 * Created by colinunger on 1/31/16.
 */
public class MatchRankingsActivityFragment extends MatchesFragment {
    String field;

    public MatchRankingsActivityFragment() {
        field = getArguments().getString("field");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new MatchRankingsActivityAdapter(getActivity().getApplicationContext(), field));
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class MatchRankingsActivityAdapter extends TeamRankingsAdapter {

        public MatchRankingsActivityAdapter(Context context, String field) {
            super(context, field, field, false);
        }
    }
}

