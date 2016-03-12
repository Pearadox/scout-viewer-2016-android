package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsFragment;

/**
 * Created by colinunger on 1/28/16.
 */
public class PredictedSeedingFragment extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new PredictedSeedingAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by colinunger on 1/28/16.
     */
    public static class PredictedSeedingAdapter extends TeamRankingsAdapter {

        public PredictedSeedingAdapter(Context context) {
            super(context, "calculatedData.predictedSeed", "calculatedData.predictedNumRPs", true);
        }
    }
}
