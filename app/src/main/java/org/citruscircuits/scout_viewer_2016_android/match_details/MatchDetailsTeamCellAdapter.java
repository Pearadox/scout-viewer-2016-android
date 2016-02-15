package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.RankingsAdapter;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 2/4/16.
 */
public class MatchDetailsTeamCellAdapter extends BaseAdapter {
    private String[] fields = {"calculatedData.actualSeed", "calculatedData.firstPickAbility", "calculatedData.highShotAccuracyTele", "calculatedData.lowShotAccuracyTele", "calculatedData.citrusDPR", "calculatedData.RScoreDrivingAbility"};
    private Integer teamNumber;

    private Context context;

    public MatchDetailsTeamCellAdapter(Context context, Integer teamNumber) {
        this.context = context;
        this.teamNumber = teamNumber;
    }

    @Override
    public int getCount() {
        return fields.length;
    }

    @Override
    public Object getItem(int position) {
        return fields[position];
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
            rowView = inflater.inflate(R.layout.team_in_match_ranking_cell, parent, false);
        }

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText(getRankOfRow(position) + "");

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        teamNumberTextView.setText(Constants.KEYS_TO_TITLES.get(getItem(position)));

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        valueTextView.setText(Utils.getObjectField(FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString()), (String)getItem(position)).toString());

        return rowView;
    }

    public int getRankOfRow(int position) {
        String fieldName = (String)getItem(position);
        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        List<Object> teams = new ArrayList<>();
        teams.addAll(FirebaseLists.teamsList.getValues());
        int rank = Utils.getRankOfObject(team, teams, fieldName);
        return rank;
    };

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
        notifyDataSetChanged();
    }
}
