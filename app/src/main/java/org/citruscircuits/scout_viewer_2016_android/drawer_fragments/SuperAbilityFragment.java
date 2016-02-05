package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.support.v4.view.PagerAdapter;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.FragmentViewPager;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick.SecondPickAbilityAdapter;

/**
 * Created by colinunger on 2/4/16.
 */
public class SuperAbilityFragment extends FragmentViewPager {
    @Override
    public PagerAdapter getPagerAdapter() {
        return new SuperAbilityAdapter(getActivity(), getChildFragmentManager());
    }
}
