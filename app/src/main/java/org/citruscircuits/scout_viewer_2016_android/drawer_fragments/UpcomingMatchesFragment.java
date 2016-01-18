package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.R;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class UpcomingMatchesFragment extends android.support.v4.app.ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new UpcomingMatchesAdapter(getActivity().getApplicationContext()));
    }
}
