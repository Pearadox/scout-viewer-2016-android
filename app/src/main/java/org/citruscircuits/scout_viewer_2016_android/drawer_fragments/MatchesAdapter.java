package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.ArrayList;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class MatchesAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Match> matches = new ArrayList<>();
    public ArrayList<String> keys = new ArrayList<>();

    public MatchesAdapter() {}

    public MatchesAdapter(Context paramContext) {
        context = paramContext;

        setupFirebaseListening();
    }

    public void setupFirebaseListening() {} //Overridden in child classes

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position);
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
            rowView = inflater.inflate(R.layout.schedule_cell, parent, false);
        }

        Match match = (Match)getItem(position);

        TextView matchTextView = (TextView)rowView.findViewById(R.id.matchNumber);
        matchTextView.setText(match.number);

        int[] teams = new int[6];
        System.arraycopy(match.redAllianceTeamNumbers, 0, teams, 0, 3);
        System.arraycopy(match.blueAllianceTeamNumbers, 0, teams, 3, 3);

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            TextView teamTextView = (TextView)rowView.findViewById(teamTextViewIDs[i]);
            teamTextView.setText(teams[i] + "");
        }

        TextView redScoreTextView = (TextView)rowView.findViewById(R.id.redScore);
        redScoreTextView.setText((match.redScore >= 0) ? match.redScore + "" : "???");

        TextView blueScoreTextView = (TextView)rowView.findViewById(R.id.blueScore);
        blueScoreTextView.setText((match.blueScore >= 0) ? match.blueScore + "" : "???");

        return rowView;
    }
}
