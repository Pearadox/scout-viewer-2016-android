package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.headerlistview.SectionAdapter;

/**
 * Created by colinunger on 1/24/16.
 */
public abstract class RankingsSectionAdapter extends SectionAdapter {

    private Context context;

    public RankingsSectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getRowView(int section, int row, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.ranking_cell, parent, false);
        }

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText(getRankOfRowInSection(section, row) + "");

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        teamNumberTextView.setText(getNameOfRowInSection(section, row));

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        valueTextView.setText(getValueOfRowInSection(section, row).toString());

        return rowView;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_separator, parent, false);
        }

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.separatorTextView);
        rankingTextView.setText((String)getSectionHeaderItem(section));

        return rowView;
    }

    public abstract int getRankOfRowInSection(int section, int row);
    public abstract String getNameOfRowInSection(int section, int row);
    public abstract Object getValueOfRowInSection(int section, int row);
}
