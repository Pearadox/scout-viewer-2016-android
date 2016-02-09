package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

        SearchView searchView = new SearchView(getActivity().getApplicationContext(), getScopes()) {
            @Override
            public void search(String searchString, String scope) {
                ((SearchableFirebaseListAdapter)getListAdapter()).searchWithTextInScope(searchString, scope);
            }
        };

        getListView().addHeaderView(searchView);
    }

    public abstract String[] getScopes();
}
