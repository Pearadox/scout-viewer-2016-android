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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.deser.Deserializers;

import org.citruscircuits.scout_viewer_2016_android.MatchDetailsActivity;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class ScheduleFragment extends MatchesFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ScheduleAdapter(getActivity().getApplicationContext()));
    }
}
