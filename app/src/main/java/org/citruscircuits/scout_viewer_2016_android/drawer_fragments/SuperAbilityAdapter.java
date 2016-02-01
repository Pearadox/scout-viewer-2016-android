package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.ObjectFieldComparator;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick.SecondPickAbilityListFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by colinunger on 1/31/16.
 */
public class SuperAbilityAdapter extends FragmentStatePagerAdapter {
    Context context;
    String[] fields = {"calculatedData.avgTorque", "calculatedData.avgSpeed", "calculatedData.avgEvasion", "calculatedData.avgDefense", "calculatedData.avgBallControl"};

    public Float avgTorque;
    public Float avgSpeed;
    public Float avgEvasion;
    public Float avgDefense;
    public Float avgBallControl;

    public SuperAbilityAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.context = context;
    }

    @Override
    public int getCount() {
        return fields.length;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString("field", fields[position]);

        SecondPickAbilityListFragment secondPickAbilityListFragment = new SecondPickAbilityListFragment();
        secondPickAbilityListFragment.setArguments(argumentsBundle);
        return secondPickAbilityListFragment;
    }
}
