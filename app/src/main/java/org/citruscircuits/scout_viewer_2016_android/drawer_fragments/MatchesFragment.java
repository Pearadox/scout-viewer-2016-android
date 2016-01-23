package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import org.citruscircuits.scout_viewer_2016_android.MatchDetailsActivity;
import org.citruscircuits.scout_viewer_2016_android.R;
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

        MatchesAdapter adapter = (MatchesAdapter)getListAdapter();
        Match matchClicked = adapter.filteredValues.get(position);

        Intent matchDetailsActivityIntent = new Intent(getActivity(), MatchDetailsActivity.class);
        matchDetailsActivityIntent.putExtra("match", matchClicked);

        startActivity(matchDetailsActivityIntent);
    }
}
