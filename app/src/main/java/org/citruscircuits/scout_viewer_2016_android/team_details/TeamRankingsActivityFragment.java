package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsFragment;

public class TeamRankingsActivityFragment extends TeamRankingsFragment {
    String field;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field = getArguments().getString("field");

        getActivity().setTitle(Constants.KEYS_TO_TITLES.get(field));
        setListAdapter(new TeamRankingsActivityAdapter(getActivity().getApplicationContext(), field));
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class TeamRankingsActivityAdapter extends TeamRankingsAdapter {

        public TeamRankingsActivityAdapter(Context context, String field) {
            super(context, field, field, false);
        }
    }
}
