package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.ObjectFieldComparator;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityAdapter extends FragmentStatePagerAdapter {
    Context context;
    List<Team> sortedTeams = new ArrayList<>();

    public SecondPickAbilityAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.context = context;

        sortedTeams.addAll(FirebaseLists.teamsList.getValues());

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                sortedTeams.clear();
                sortedTeams.addAll(FirebaseLists.teamsList.getValues());
                Collections.sort(sortedTeams, new ObjectFieldComparator("calculatedData.firstPickAbility", true));
                notifyDataSetChanged();
            }
        }, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));
    }

    @Override
    public int getCount() {
        return sortedTeams.size();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putInt("teamNumber", sortedTeams.get(position).number);

        SecondPickAbilityListFragment secondPickAbilityListFragment = new SecondPickAbilityListFragment();
        secondPickAbilityListFragment.setArguments(argumentsBundle);
        return secondPickAbilityListFragment;
    }
}
