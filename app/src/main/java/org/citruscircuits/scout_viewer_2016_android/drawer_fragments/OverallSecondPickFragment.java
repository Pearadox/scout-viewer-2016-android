package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsFragment;

public class OverallSecondPickFragment extends TeamRankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new OverallSecondPickAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by colinunger on 1/28/16.
     */
    public static class OverallSecondPickAdapter extends TeamRankingsAdapter {

        public OverallSecondPickAdapter(Context context) {
            super(context, "calculatedData.overallSecondPickAbility", "calculatedData.overallSecondPickAbility", false);
        }
    }
}
