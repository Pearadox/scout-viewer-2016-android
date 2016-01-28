package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseList;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.TeamValueComparator;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityAdapter extends FragmentStatePagerAdapter {
    Context ctxt = null;
    FirebaseList teamList;
    ArrayList<Team> sortedTeams;

    public SecondPickAbilityAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);
        this.ctxt=ctxt;

        teamList = new FirebaseList(Constants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                ArrayList<Team> teams = new ArrayList(teamList.values());
                Collections.sort(teams, new TeamValueComparator(true, new TeamValueComparator.TeamValueRetriever() {
                    @Override
                    public Comparable retrieve(Team t) {
                        return (Float)Utils.getObjectField(t, "calculatedData.firstPickAbility");
                    }
                }));
                sortedTeams = teams;
            }
        });
    }

    @Override
    public int getCount() {
        return sortedTeams.size();
    }

    @Override
    public Fragment getItem(int position) {
        return(SecondPickAbilityListFragment.newInstance(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }


    //    @Override
//    public String getPageTitle(int position) {
//        return "Second Pick Ability";
//    }
}
