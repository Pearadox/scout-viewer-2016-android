package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class ScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ScheduleAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/16/16.
     */
    public static class ScheduleAdapter extends MatchesAdapter {
        public ScheduleAdapter(Context context) {
            super(context, true);
        }

        @Override
        public boolean secondaryFilter(Match value) {
            return true;
        }

        @Override
        public boolean shouldBoldTextViewWithText(String text) {
            return false;
        }
    }
}
