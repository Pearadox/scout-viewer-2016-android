package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityListFragment extends RankingsFragment {
    private static final String KEY_POSITION = "position";
    private static Team team;

    public SecondPickAbilityListFragment(Team t) {
        team = t;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SecondPickAbilityListAdapter(getActivity().getApplicationContext(), ));

        int position=getArguments().getInt(KEY_POSITION, -1);
    }

    static SecondPickAbilityListFragment newInstance(int position, Team t) {
        SecondPickAbilityListFragment frag = new SecondPickAbilityListFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

//    static String getTitle(Context ctxt, int position) {
//        return(String.format(ctxt.getString(R.string.hint), position + 1));
//    }
}
