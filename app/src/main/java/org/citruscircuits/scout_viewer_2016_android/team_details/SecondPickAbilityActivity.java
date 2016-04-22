package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.ViewerActivity;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.TeamScheduleFragment;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick.SecondPickAbilityFragment;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick.SecondPickAbilityListFragment;

public class SecondPickAbilityActivity extends ViewerActivity {

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_second_pick_ability);
        String field = getIntent().getStringExtra("field");
        setTitle(Constants.KEYS_TO_TITLES.get(field));
        int teamNumber = getIntent().getIntExtra("teamNumber", 1678);

        Fragment fragment = new SecondPickAbilityListFragment();
        Bundle args = new Bundle();
        args.putInt("teamNumber", teamNumber);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.secondPickAbilityActivityLayout, fragment, "").commit();
    }
}
