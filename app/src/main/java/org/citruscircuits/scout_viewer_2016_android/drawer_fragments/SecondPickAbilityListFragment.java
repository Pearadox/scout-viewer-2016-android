package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class SecondPickAbilityListFragment extends RankingsFragment {
    private static final String KEY_POSITION = "position";
    private int teamNumber;

    public SecondPickAbilityListFragment(int t) {
        teamNumber = t;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SecondPickAbilityListAdapter(getActivity().getApplicationContext(), teamNumber));

        int position=getArguments().getInt(KEY_POSITION, -1);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View secondPickTeamTitleListHeader = View.inflate(getActivity().getApplicationContext(), R.layout.second_pick_list_header, null);
        TextView secondPickTeamTitleTextView = (TextView)secondPickTeamTitleListHeader.findViewById(R.id.secondPickTeamTitleTextView);
        secondPickTeamTitleTextView.setText(teamNumber + "");
        getListView().addHeaderView(secondPickTeamTitleListHeader, null, false);
    }

    static SecondPickAbilityListFragment newInstance(int position, int t) {
        SecondPickAbilityListFragment frag = new SecondPickAbilityListFragment(t);
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

//    static String getTitle(Context ctxt, int position) {
//        return(String.format(ctxt.getString(R.string.hint), position + 1));
//    }
}
