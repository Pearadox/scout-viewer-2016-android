package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.TeamValueComparator;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.Comparator;

/**
 * Created by citruscircuits on 1/18/16.
 */
public abstract class RankingsAdapter extends SearchableFirebaseListAdapter<Team> {
    public Context context;
    public static TeamValueComparator.TeamValueRetriever valueRetriever;

    public RankingsAdapter(Context paramContext, TeamValueComparator.TeamValueRetriever valueRetriever, boolean isNotReversed) {
        super(Team.class, Constants.TEAMS_PATH, new TeamValueComparator(isNotReversed, valueRetriever));
        this.valueRetriever = valueRetriever;
        context = paramContext;
    }

    @Override
    public int getCount() {
        return filteredValues.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredValues.get(position);
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

        Team team = (Team)getItem(position);

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText(filteredValues.indexOf(team) + 1 + "");

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        teamNumberTextView.setText(team.number + "");

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        valueTextView.setText(valueRetriever.retrieve(team).toString() + "");

        return rowView;
    }

    @Override
    public boolean filter(Team value) {
        String teamNumberString = value.number + "";
        return teamNumberString.contains(searchString);
    }
}
