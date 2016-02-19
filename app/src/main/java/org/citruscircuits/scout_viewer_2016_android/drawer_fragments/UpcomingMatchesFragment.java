package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;

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
            return value.number > StarManager.getCurrentMatchNumber();
        }

        @Override
        public boolean shouldHighlightTextViewWithText(String text) {
            return false;
        }
    }
}
