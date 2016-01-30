package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class RankingsActivityFragment extends RankingsFragment {
    String field;

    public RankingsActivityFragment() {
//        field = getArguments().getString("field");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new RankingsActivityAdapter(getActivity().getApplicationContext()));
    }

    /**
     * Created by citruscircuits on 1/27/16.
     */
    public static class RankingsActivityAdapter extends RankingsAdapter {

        public RankingsActivityAdapter(Context context) {
            super(context, "calculatedData.firstPickAbility", false);
        }
    }
}
