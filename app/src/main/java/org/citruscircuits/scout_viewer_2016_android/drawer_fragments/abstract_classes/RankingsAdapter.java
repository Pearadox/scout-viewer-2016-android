package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.ObjectFieldComparator;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.List;

/**
 * Created by citruscircuits on 1/18/16.
 */
public abstract class RankingsAdapter extends SearchableFirebaseListAdapter<Team> {
    private String fieldName;

    public RankingsAdapter(Context context, String fieldName, boolean isNotReversed) {
        super(context, new ObjectFieldComparator(fieldName, isNotReversed));
        this.fieldName = fieldName;
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
        teamNumberTextView.setText(team.number.toString());

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        valueTextView.setText(Utils.getObjectField(team, fieldName).toString());

        return rowView;
    }

    @Override
    public boolean filter(Team value) {
        String teamNumberString = value.number.toString();
        return teamNumberString.contains(searchString);
    }

    @Override
    public List<Team> getFirebaseList() {
        return FirebaseLists.teamsList.getValues();
    }

    @Override
    public String getBroadcastAction() {
        return Constants.TEAMS_UPDATED_ACTION;
    }
}
