package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.util.Log;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.Comparator;

/**
 * Created by colinunger on 1/23/16.
 */
public abstract class SearchableFirebaseListAdapter<T> extends FirebaseListAdapter<T> {
    public String searchString;

    public SearchableFirebaseListAdapter(Class<T> mModelClass, String pathToListenTo, Comparator<T> filterComparator) {
        super(mModelClass, pathToListenTo, filterComparator);
    }

    public void searchWithText(String searchString) {
        this.searchString = searchString;
        filterValues();
        notifyDataSetChanged();
    }
}
