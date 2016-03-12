package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.TeamRankingsFragment;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityListFragment extends TeamRankingsFragment {
    private Integer teamNumber;

    public SecondPickAbilityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamNumber = getArguments().getInt("teamNumber");
        setListAdapter(new SecondPickAbilityListAdapter(getActivity().getApplicationContext(), teamNumber));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View secondPickTeamTitleListHeader = View.inflate(getActivity().getApplicationContext(), R.layout.second_pick_list_header, null);
        TextView secondPickTeamTitleTextView = (TextView)secondPickTeamTitleListHeader.findViewById(R.id.secondPickTeamTitleTextView);
        secondPickTeamTitleTextView.setText(teamNumber + "");
        getListView().addHeaderView(secondPickTeamTitleListHeader, null, false);
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class SecondPickAbilityListAdapter extends TeamRankingsAdapter {

        public SecondPickAbilityListAdapter(Context context, int teamNumber) {
            super(context, "calculatedData.secondPickAbility." + teamNumber, "calculatedData.secondPickAbility." + teamNumber, false);
        }
    }
}
