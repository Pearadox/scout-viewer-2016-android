package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.ObjectFieldComparator;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.match_details.MatchDetailsActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
    public boolean isEnabled(int position) {
        return false;
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

        if (ViewerApplication.isStarredMatch(match.number)) {
            rowView.setBackgroundColor(Color.argb(60, 228, 230, 0));
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT);
        }

        TextView matchTextView = (TextView)rowView.findViewById(R.id.matchNumber);
        if (selectedScope == "Match") {
            matchTextView.setText(Utils.highlightTextInString(match.number.toString(), searchString));
        } else {
            matchTextView.setText(match.number.toString());
        }

        List<Integer> teamsInMatch = new ArrayList<>();
        teamsInMatch.addAll(match.redAllianceTeamNumbers);
        teamsInMatch.addAll(match.blueAllianceTeamNumbers);

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            TextView teamTextView = (TextView)rowView.findViewById(teamTextViewIDs[i]);
            if (selectedScope == "Team") {
                teamTextView.setText(Utils.highlightTextInString(teamsInMatch.get(i).toString(), searchString));
            } else {
                teamTextView.setText(teamsInMatch.get(i).toString());
            }
        }

        TextView redScoreTextView = (TextView)rowView.findViewById(R.id.redScore);
        redScoreTextView.setText((match.redScore >= 0) ? match.redScore.toString() : "???");

        TextView blueScoreTextView = (TextView)rowView.findViewById(R.id.blueScore);
        blueScoreTextView.setText((match.blueScore >= 0) ? match.blueScore.toString() : "???");

        rowView.setOnLongClickListener(new StarLongClickListener());
        rowView.setOnClickListener(new StarClickListener());
        return rowView;
    }

    @Override
    public boolean filter(Match value, String scope) {
        List<Integer> teamsInMatch = new ArrayList<>();
        teamsInMatch.addAll(value.redAllianceTeamNumbers);
        teamsInMatch.addAll(value.blueAllianceTeamNumbers);

        boolean found = false;
        if (secondaryFilter(value)) {
            if (searchString.length() == 0) {
                found = true;
            } else if (scope == "Team") {
                for (Integer team : teamsInMatch) {
                    if (team.toString().indexOf(searchString) == 0) {
                        found = true;
                    }
                }
            } else if (scope == "Match") {
                found = value.number.toString().indexOf(searchString) == 0;
            }
        }

        return found;
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

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView matchNumberTextView = (TextView)v.findViewById(R.id.matchNumber);
            if (ViewerApplication.isStarredMatch(Integer.parseInt(matchNumberTextView.getText().toString()))) {
                ViewerApplication.removeStarredMatch(Integer.parseInt(matchNumberTextView.getText().toString()));
            } else {
                ViewerApplication.addStarredMatch(Integer.parseInt(matchNumberTextView.getText().toString()));
            }
            notifyDataSetChanged();
            return true;
        }
    }

    private class StarClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView matchNumberTextView = (TextView)v.findViewById(R.id.matchNumber);
            Integer matchNumberClicked = Integer.parseInt(matchNumberTextView.getText().toString());

            Intent matchDetailsActivityIntent = new Intent(context, MatchDetailsActivity.class);
            matchDetailsActivityIntent.putExtra("matchNumber", matchNumberClicked);
            matchDetailsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(matchDetailsActivityIntent);
        }
    }
}
