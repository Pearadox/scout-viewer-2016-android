package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.R;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.second_pick_list_header, container, false);
        ViewPager pager = (ViewPager) result.findViewById(R.id.secondPickAbilityPager);

        pager.setAdapter(buildAdapter());

        return (result);
    }

    private SecondPickAbilityAdapter buildAdapter() {
        return (new SecondPickAbilityAdapter(getActivity(), getChildFragmentManager()));
    }
}
