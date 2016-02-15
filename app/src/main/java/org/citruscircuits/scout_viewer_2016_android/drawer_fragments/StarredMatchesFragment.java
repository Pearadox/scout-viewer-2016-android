package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;

/**
 * Created by colinunger on 2/14/16.
 */
public class StarredMatchesFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new StarredMatchesAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/16/16.
     */
    public static class StarredMatchesAdapter extends MatchesAdapter {
        public StarredMatchesAdapter(Context context) {
            super(context, true);
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return StarManager.isImportantMatch(value.number);
        }

        @Override
        public boolean shouldHighlightTextViewWithText(String text) {
            return false;
        }
    }
}
