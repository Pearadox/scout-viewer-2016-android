package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.match_details.MatchDetailsActivity;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

/**
 * Created by colinunger on 1/23/16.
 */
public abstract class MatchesFragment extends SearchableListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        SearchableFirebaseListAdapter<Match> adapter = (SearchableFirebaseListAdapter)getListAdapter();
        int matchNumberClicked = adapter.filteredValues.get(position - getListView().getHeaderViewsCount()).number;

        Intent matchDetailsActivityIntent = new Intent(getActivity(), MatchDetailsActivity.class);
        matchDetailsActivityIntent.putExtra("matchNumber", matchNumberClicked);

        startActivity(matchDetailsActivityIntent);
    }



    @Override
    public String[] getScopes() {
        return Constants.MATCH_SCOPES;
    }
}
