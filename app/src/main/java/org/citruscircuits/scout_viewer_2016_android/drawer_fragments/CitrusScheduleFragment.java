package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

/**
 * Created by colinunger on 2/8/16.
 */
public class CitrusScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new CitrusScheduleAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class CitrusScheduleAdapter extends MatchesAdapter {

        public CitrusScheduleAdapter(Context context) {
            super(context, true);
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return (value.redAllianceTeamNumbers.contains(1678) || value.blueAllianceTeamNumbers.contains(1678));
        }

        @Override
        public boolean shouldBoldTextViewWithText(String text) {
            return text.equals("1678");
        }
    }
}
