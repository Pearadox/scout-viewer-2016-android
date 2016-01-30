package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsFragment;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class FirstPickAbilityFragment extends RankingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new FirstPickAbilityAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class FirstPickAbilityAdapter extends RankingsAdapter {

        public FirstPickAbilityAdapter(Context context) {
            super(context, "calculatedData.firstPickAbility", false);
        }
    }
}
