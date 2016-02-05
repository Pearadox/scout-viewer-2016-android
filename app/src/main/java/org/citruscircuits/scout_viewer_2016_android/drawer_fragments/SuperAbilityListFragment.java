package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsFragment;

/**
 * Created by colinunger on 1/31/16.
 */
public class SuperAbilityListFragment extends TeamRankingsFragment {
    private String field;

    public SuperAbilityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        field = getArguments().getString("field");
        setListAdapter(new SuperAbilityListAdapter(getActivity().getApplicationContext(), field));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View secondPickTeamTitleListHeader = View.inflate(getActivity().getApplicationContext(), R.layout.second_pick_list_header, null);
        TextView secondPickTeamTitleTextView = (TextView)secondPickTeamTitleListHeader.findViewById(R.id.secondPickTeamTitleTextView);
        secondPickTeamTitleTextView.setText(Constants.KEYS_TO_TITLES.get(field));
        getListView().addHeaderView(secondPickTeamTitleListHeader, null, false);
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class SuperAbilityListAdapter extends TeamRankingsAdapter {

        public SuperAbilityListAdapter(Context context, String field) {
            super(context, field, false);
        }
    }
}
