package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.headerlistview.SectionAdapter;
import com.firebase.client.Firebase;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class TeamDetailsSectionAdapter extends SectionAdapter {

    private String[][] fieldsToDisplay = {{"avgHighShotsAuto", "avgLowShotsAuto"}, {"avgHighShotsTele", "avgLowShotsTele"}};
    private String[] sectionTitles = {"Auto", "Teleop"};
    private Context context;

    public TeamDetailsSectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int numberOfSections() {
        return 2;
    }

    @Override
    public int numberOfRows(int section) {
        return 50;
    }

    @Override
    public View getRowView(int section, int row, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.ranking_cell, parent, false);
        }

//        Team team = (Team)getItem(position);

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText("R");

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        teamNumberTextView.setText("Team");

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        valueTextView.setText("Value");

        return rowView;
    }

    @Override
    public Object getRowItem(int section, int row) {
        return fieldsToDisplay[section][row];
    }

    @Override
    public Object getSectionHeaderItem(int section) {
        return sectionTitles[section];
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_separator, parent, false);
        }

//        Team team = (Team)getItem(position);

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.separatorTextView);
        rankingTextView.setText((String)getSectionHeaderItem(section));

        return rowView;
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }
}
