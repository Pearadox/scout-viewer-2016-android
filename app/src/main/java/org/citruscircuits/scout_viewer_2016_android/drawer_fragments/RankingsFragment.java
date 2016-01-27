package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.TeamDetailsActivity;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by colinunger on 1/22/16.
 */
public abstract class RankingsFragment extends SearchableListFragment {

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        RankingsAdapter adapter = (RankingsAdapter)getListAdapter();
        int teamNumberClicked = adapter.filteredValues.get(position - 1).number;

        Intent teamDetailsViewIntent = new Intent(getActivity(), TeamDetailsActivity.class);
        teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);

        startActivity(teamDetailsViewIntent);
    }
}
