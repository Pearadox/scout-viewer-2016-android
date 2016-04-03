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
        Boolean displayValueAsPercentage = getArguments().getBoolean("displayValueAsPercentage");


        getActivity().setTitle(Constants.KEYS_TO_TITLES.get(field));
        setListAdapter(new TeamRankingsActivityAdapter(getActivity().getApplicationContext(), field, displayValueAsPercentage));
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class TeamRankingsActivityAdapter extends TeamRankingsAdapter {
        private Boolean displayValueAsPercentage;
        public TeamRankingsActivityAdapter(Context context, String field, Boolean displayValueAsPercentage) {
            super(context, field, field, false);
            this.displayValueAsPercentage = displayValueAsPercentage;
        }
        @Override
        public Boolean displayValueAsPercentage() {
            return displayValueAsPercentage;
        }
    }
}
