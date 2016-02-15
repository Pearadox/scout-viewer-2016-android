package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.SearchableFirebaseListAdapter;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.List;

/**
 * Created by colinunger on 2/13/16.
 */
public class DefenseDetailsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        String defense = getIntent().getStringExtra("defense");
        setTitle(Constants.KEYS_TO_TITLES.get(defense));

        FragmentManager fragmentManager = getSupportFragmentManager();
        DefenseDetailsActivityFragment fragment = new DefenseDetailsActivityFragment();

        Bundle args = new Bundle();
        args.putString("defense", defense);
        args.putInt("teamNumber", getIntent().getIntExtra("teamNumber", 0));

        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();
    }


}
