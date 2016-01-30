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
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by citruscircuits on 1/17/16.
 */
public abstract class MatchesAdapter extends SearchableFirebaseListAdapter<Match> {
    public Context context;

    public MatchesAdapter(Context context, boolean isNotReversed) {
        super(context, new ObjectFieldComparator("number", isNotReversed));
        this.context = context;

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
            rowView = inflater.inflate(R.layout.schedule_cell, parent, false);
        }

        Match match = (Match)getItem(position);

        TextView matchTextView = (TextView)rowView.findViewById(R.id.matchNumber);
        matchTextView.setText(match.number.toString());

        List<Integer> teamsInMatch = new ArrayList<>();
        teamsInMatch.addAll(match.redAllianceTeamNumbers);
        teamsInMatch.addAll(match.blueAllianceTeamNumbers);

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            TextView teamTextView = (TextView)rowView.findViewById(teamTextViewIDs[i]);
            teamTextView.setText(teamsInMatch.get(i).toString());
        }

        TextView redScoreTextView = (TextView)rowView.findViewById(R.id.redScore);
        redScoreTextView.setText((match.redScore >= 0) ? match.redScore.toString() : "???");

        TextView blueScoreTextView = (TextView)rowView.findViewById(R.id.blueScore);
        blueScoreTextView.setText((match.blueScore >= 0) ? match.blueScore.toString() : "???");

        return rowView;
    }

    @Override
    public boolean filter(Match value) {
        List<Integer> teamsInMatch = new ArrayList<>();
        teamsInMatch.addAll(value.redAllianceTeamNumbers);
        teamsInMatch.addAll(value.blueAllianceTeamNumbers);

        if (secondaryFilter(value)) {
            for (Integer team : teamsInMatch) {
                String teamNumberString = team.toString();
                if (teamNumberString.contains(searchString)) {
                    return true;
                }
            }

            String matchNumberString = value.number.toString();
            if (matchNumberString.contains(searchString)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getBroadcastAction() {
        return Constants.MATCHES_UPDATED_ACTION;
    }

    @Override
    public List<Match> getFirebaseList() {
        return FirebaseLists.matchesList.getValues();
    }

    public abstract boolean secondaryFilter (Match value);
}
