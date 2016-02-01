package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick.SecondPickAbilityAdapter;

/**
 * Created by colinunger on 1/31/16.
 */
public abstract class FragmentViewPager extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.second_pick_view_pager, container, false);
        ViewPager pager = (ViewPager) result.findViewById(R.id.secondPickAbilityPager);

        pager.setAdapter(getPagerAdapter());

        return (result);
    }

    public abstract PagerAdapter getPagerAdapter();
}
