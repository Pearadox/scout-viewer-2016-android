package org.citruscircuits.scout_viewer_2016_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public abstract class RankingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        String field = getIntent().getStringExtra("field");
        setTitle(Constants.KEYS_TO_TITLES.get(field));

        Fragment fragment = getFragment();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString("field", field);
        fragment.setArguments(argumentsBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.rankingsLinearLayout, fragment, "").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_graph) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, new GraphFragment(), "").commit();
        }
        return true;
    }

    public abstract Fragment getFragment();

}
