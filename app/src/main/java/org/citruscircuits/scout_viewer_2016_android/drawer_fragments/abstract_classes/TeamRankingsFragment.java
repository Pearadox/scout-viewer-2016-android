package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsActivity;

/**
 * Created by colinunger on 1/22/16.
 */
public abstract class TeamRankingsFragment extends SearchableListFragment {

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        TeamRankingsAdapter adapter = (TeamRankingsAdapter)getListAdapter();
        int teamNumberClicked = adapter.filteredValues.get(position - getListView().getHeaderViewsCount()).number;

        Intent teamDetailsViewIntent = new Intent(getActivity(), TeamDetailsActivity.class);
        teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);

        startActivity(teamDetailsViewIntent);
    }
}
