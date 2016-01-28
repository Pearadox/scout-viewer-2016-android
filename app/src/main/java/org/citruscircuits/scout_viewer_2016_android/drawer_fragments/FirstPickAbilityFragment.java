package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.os.Bundle;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class FirstPickAbilityFragment extends RankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new FirstPickAbilityAdapter(getActivity().getApplicationContext()));
    }
}
