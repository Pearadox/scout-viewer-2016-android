package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

/**
 * Created by colinunger on 2/8/16.
 */
public class TeamScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer teamNumber = getArguments().getInt("teamNumber");

        setListAdapter(new CitrusScheduleAdapter(getActivity().getApplicationContext(), teamNumber));
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class CitrusScheduleAdapter extends MatchesAdapter {
        Integer teamNumber;


        public CitrusScheduleAdapter(Context context, Integer teamNumber) {
            super(context, true);
            this.teamNumber = teamNumber;
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return (value.redAllianceTeamNumbers.contains(teamNumber) || value.blueAllianceTeamNumbers.contains(teamNumber));
        }

        @Override
        public boolean shouldHighlightTextViewWithText(String text) {
            return text.equals(teamNumber.toString());
        }
    }
}
