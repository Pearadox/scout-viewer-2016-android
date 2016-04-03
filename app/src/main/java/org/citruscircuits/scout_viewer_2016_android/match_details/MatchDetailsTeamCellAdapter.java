package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.Context;
import android.content.Intent;
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
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by colinunger on 2/4/16.
 */
public class MatchDetailsTeamCellAdapter extends BaseAdapter {
    private String[] fields = {"calculatedData.actualSeed", "calculatedData.firstPickAbility", "calculatedData.highShotAccuracyTele", "calculatedData.lowShotAccuracyTele", "calculatedData.RScoreDrivingAbility"};
    private String[] fieldsToDisplayAsPercentages = {"calculatedData.highShotAccuracyTele", "calculatedData.lowShotAccuracyTele"};
    private String[] reverseRankFields = {"calculatedData.actualSeed"};
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
        rankingTextView.setText(getRankTextOfRow(position));

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        teamNumberTextView.setText(Constants.KEYS_TO_TITLES.get(getItem(position)));

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        if (Arrays.asList(fieldsToDisplayAsPercentages).contains(getItem(position))) {
            valueTextView.setText(Utils.dataPointToPercentage((Float)Utils.getObjectField(team, (String)getItem(position)), 0));
        } else {
            valueTextView.setText(Utils.getDisplayValue(team, (String) getItem(position)));
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent matchDetailsTeamCellClickedIntent = new Intent(context, TeamDetailsActivity.class);
                matchDetailsTeamCellClickedIntent.putExtra("teamNumber", teamNumber);
                context.startActivity(matchDetailsTeamCellClickedIntent);
            }
        });
        return rowView;
    }

    public String getRankTextOfRow(int position) {
        String fieldName = (String)getItem(position);
        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        List<Object> teams = new ArrayList<>();
        teams.addAll(FirebaseLists.teamsList.getValues());
        Integer rank = Utils.getRankOfObject(team, teams, fieldName, Arrays.asList(reverseRankFields).contains(fieldName));
        return (rank != null) ? (++rank).toString() : "?";
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
