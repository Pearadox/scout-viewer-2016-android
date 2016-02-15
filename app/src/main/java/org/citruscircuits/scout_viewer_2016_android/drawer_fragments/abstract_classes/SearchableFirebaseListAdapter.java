package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by colinunger on 1/23/16.
 */
public abstract class SearchableFirebaseListAdapter<T> extends BaseAdapter {
    public String searchString;
    public String selectedScope;
    public List<T> filteredValues = new ArrayList<>();
    Comparator<T> filterComparator;
    public Context context;
    private BroadcastReceiver broadcastReceiver;
    private BroadcastReceiver starReceiver;

    public SearchableFirebaseListAdapter(Context context, Comparator<T> filterComparator) {
        this.filterComparator = filterComparator;
        this.context = context;
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                searchWithTextInScope(searchString, selectedScope);
                notifyDataSetChanged();
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(getBroadcastAction()));

        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyDataSetChanged();
            }
        };
        LocalBroadcastManager.getInstance(context).registerReceiver(starReceiver, new IntentFilter(Constants.STARS_MODIFIED_ACTION));
    }

    public void searchWithTextInScope(String searchString, String scope) {
        this.searchString = searchString;
        this.selectedScope = scope;
        filterValues();
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        filterValues();

        super.notifyDataSetChanged();
    }

    public void filterValues() {
        filteredValues.clear();
        for (T value : getFirebaseList()) {
            if(filter(value, selectedScope)) {
                filteredValues.add(value);
            }
        }

        Collections.sort(filteredValues, filterComparator);
    }

    public abstract String getBroadcastAction();

    public abstract boolean filter(T value, String scope);

    public abstract List<T> getFirebaseList();

//    public abstract String[] getSearchScopes();
    public void cleanup() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }
}
