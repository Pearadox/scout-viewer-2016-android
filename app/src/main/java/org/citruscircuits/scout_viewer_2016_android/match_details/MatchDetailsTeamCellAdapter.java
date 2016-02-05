package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.R;

/**
 * Created by colinunger on 2/4/16.
 */
public class MatchDetailsTeamCellAdapter extends BaseAdapter {
    private Context context;

    public MatchDetailsTeamCellAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return "1678";
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

//        TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
//        textView.setText(getItem(position) + "");
        return rowView;
    }
}
