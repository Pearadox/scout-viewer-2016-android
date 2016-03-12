package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.SearchableFirebaseListAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.SearchableListFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamRankingsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by colinunger on 2/13/16.
 */
public class DefenseDetailsActivityFragment extends SearchableListFragment {
    Integer teamNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamNumber = getArguments().getInt("teamNumber");
        String defense = getArguments().getString("defense");

        setListAdapter(new DefenseDetailsActivityFragment.DefenseDetailsFragmentAdapter(getActivity().getApplicationContext(), teamNumber, defense));
        Log.i("SDJKFN", "HERE!");
    }

    @Override
    public String[] getScopes() {
        return new String[] {"keys"};
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent rankingsActivityIntent = new Intent(getActivity().getApplicationContext(), TeamRankingsActivity.class);
        rankingsActivityIntent.putExtra("team", teamNumber);
        rankingsActivityIntent.putExtra("field", (String)getListAdapter().getItem(position - l.getHeaderViewsCount()));

//        rankingsActivityIntent.putExtra("teamNumber", teamNumber);
        rankingsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().getApplicationContext().startActivity(rankingsActivityIntent);
    }

    /**
     * Created by citruscircuits on 1/17/16.
     */
    public static class DefenseDetailsFragmentAdapter extends SearchableFirebaseListAdapter<String> {

//        String[] keysToDisplay = {"calculatedData.avgSuccessfulTimesCrossedDefensesAuto.a.pc"};
        Integer teamNumber;
        String defense;

        public DefenseDetailsFragmentAdapter(Context context, Integer teamNumber, String defense) {
            super(context, new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    return lhs.compareTo(rhs);
                }
            });
            this.teamNumber = teamNumber;
            this.defense = defense;
        }

        @Override
        public String getBroadcastAction() {
            return Constants.TEAMS_UPDATED_ACTION;
        }

        @Override
        public boolean filter(String value, String scope) {
            return true;
        }

        @Override
        public List<String> getFirebaseList() {
            return Arrays.asList(getFieldsToDisplayForDefense(defense));
        }

        @Override
        public int getCount() {
            return getFieldsToDisplayForDefense(defense).length;
        }

        @Override
        public Object getItem(int position) {
            return getFieldsToDisplayForDefense(defense)[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.ranking_cell, parent, false);
            }

            String value = (String)getItem(position);
            Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());

            TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
            Log.i("NKJSD", filteredValues.indexOf(value) + 1 + "");
            Log.i("BHG", value);
            String rank;
            try {
                rank = Integer.toString(Utils.getRankOfObject(team, new ArrayList<Object>(FirebaseLists.teamsList.getValues()), value));
            } catch (NullPointerException |NumberFormatException ne) {
                rank = "?";
            }
            Log.i("ZIN", rank);
            rankingTextView.setText(rank);

            TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
            if (searchString.length() > 0) {
                teamNumberTextView.setText(Constants.KEYS_TO_TITLES.get(value));
            } else {
                teamNumberTextView.setText(Constants.KEYS_TO_TITLES.get(value));
            }

            TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
            valueTextView.setText(Utils.getDisplayValue(team, value));

            return rowView;
        }

        public String[] getFieldsToDisplayForDefense(String defense) {
            return new String [] {"calculatedData.avgSuccessfulTimesCrossedDefensesAuto." + defense,
                "calculatedData.avgFailedTimesCrossedDefensesAuto." + defense,
                "calculatedData.avgSuccessfulTimesCrossedDefensesTele." + defense,
                "calculatedData.avgFailedTimesCrossedDefensesTele." + defense,
                "calculatedData.avgSuccessfulTimesCrossedDefenses." + defense};
        }
    }



}
