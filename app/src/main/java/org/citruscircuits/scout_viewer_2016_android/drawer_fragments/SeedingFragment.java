package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.os.Bundle;

/**
 * Created by colinunger on 1/28/16.
 */
public class SeedingFragment extends RankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SeedingAdapter(getActivity().getApplicationContext()));
    }
}