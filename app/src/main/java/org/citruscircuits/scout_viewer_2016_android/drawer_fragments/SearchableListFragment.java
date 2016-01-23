package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.citruscircuits.scout_viewer_2016_android.R;

/**
 * Created by colinunger on 1/23/16.
 */
public class SearchableListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        final SearchableFirebaseListAdapter searchableFirebaseListAdapter = (SearchableFirebaseListAdapter)getListAdapter();
        searchableFirebaseListAdapter.searchWithText("");
        EditText searchBar = (EditText)rootView.findViewById(R.id.listSearchEditText);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                searchableFirebaseListAdapter.searchWithText(s.toString());
            }
        });

        return rootView;
    }
}
