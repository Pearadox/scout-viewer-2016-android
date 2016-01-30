package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.citruscircuits.scout_viewer_2016_android.R;

/**
 * Created by colinunger on 1/23/16.
 */
public abstract class SearchableListFragment extends ListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View headerView = inflater.inflate(R.layout.search_header, null);

        final SearchableFirebaseListAdapter searchableFirebaseListAdapter = (SearchableFirebaseListAdapter)getListAdapter();
        searchableFirebaseListAdapter.searchWithText("");

        EditText searchBar = (EditText)headerView.findViewById(R.id.listSearchEditText);
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

        getListView().addHeaderView(headerView, null, false);
    }
}
