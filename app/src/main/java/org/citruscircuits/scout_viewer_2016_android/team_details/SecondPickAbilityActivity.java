package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.TeamScheduleFragment;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick.SecondPickAbilityFragment;

public class SecondPickAbilityActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_pick_ability);
        String field = getIntent().getStringExtra("field");
        setTitle(Constants.KEYS_TO_TITLES.get(field));

        Fragment fragment = new SecondPickAbilityFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.secondPickAbilityActivityLayout, fragment, "").commit();
    }
}
