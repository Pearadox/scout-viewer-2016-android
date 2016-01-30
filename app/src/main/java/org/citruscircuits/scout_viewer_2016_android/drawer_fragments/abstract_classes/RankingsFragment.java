package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import org.citruscircuits.scout_viewer_2016_android.TeamDetailsActivity;

/**
 * Created by colinunger on 1/22/16.
 */
public abstract class RankingsFragment extends SearchableListFragment {

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        RankingsAdapter adapter = (RankingsAdapter)getListAdapter();
        int teamNumberClicked = adapter.filteredValues.get(position).number;

        Intent teamDetailsViewIntent = new Intent(getActivity(), TeamDetailsActivity.class);
        teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);

        startActivity(teamDetailsViewIntent);
    }
}
