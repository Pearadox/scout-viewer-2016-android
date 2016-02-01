package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.second_pick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.FragmentViewPager;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityFragment extends FragmentViewPager {
    @Override
    public PagerAdapter getPagerAdapter() {
        return new SecondPickAbilityAdapter(getActivity(), getChildFragmentManager());
    }
}
