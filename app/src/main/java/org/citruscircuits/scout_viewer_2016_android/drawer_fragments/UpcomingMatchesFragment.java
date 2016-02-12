package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class UpcomingMatchesFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new UpcomingMatchesAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class UpcomingMatchesAdapter extends MatchesAdapter {

        public UpcomingMatchesAdapter(Context context) {
            super(context, true);
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return value.redScore == -1 && value.blueScore == -1;
        }

        @Override
        public boolean shouldBoldTextViewWithText(String text) {
            return false;
        }
    }
}
