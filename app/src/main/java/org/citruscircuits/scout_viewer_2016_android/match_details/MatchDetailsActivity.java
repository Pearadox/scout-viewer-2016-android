package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

public class MatchDetailsActivity extends ActionBarActivity {
    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        int matchNumber = getIntent().getIntExtra("matchNumber", 0);

        Firebase matchRef = new Firebase(Constants.MATCHES_PATH + "/" + matchNumber);
        matchRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                match = dataSnapshot.getValue(Match.class);
                updateUI();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void updateUI() {
        LinearLayout redTeamsLinearLayout = (LinearLayout)findViewById(R.id.matchDetailsRedTeamsLinearLayout);
        for (Integer redTeamNumber : match.redAllianceTeamNumbers) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            View teamInMatchDetailsCell = getTeamInMatchCell(redTeamNumber, true);
            redTeamsLinearLayout.addView(teamInMatchDetailsCell, params);
        }

        LinearLayout blueTeamsLinearLayout = (LinearLayout)findViewById(R.id.matchDetailsBlueTeamsLinearLayout);
        for (Integer blueTeamNumber : match.blueAllianceTeamNumbers) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            View teamInMatchDetailsCell = getTeamInMatchCell(blueTeamNumber, false);
            blueTeamsLinearLayout.addView(teamInMatchDetailsCell, params);
        }

        TextView matchDetailsMatchTitleTextView = (TextView)findViewById(R.id.matchDetailsMatchTitleTextView);
        matchDetailsMatchTitleTextView.setText("Q" + match.number.toString());

        TextView redAllianceScoreTextView = (TextView)findViewById(R.id.matchDetailsRedAllianceScore);
        TextView redAlliancePredictedScoreTextView = (TextView)findViewById(R.id.matchDetailsRedAlliancePredictedScore);
        TextView redAllianceWinChanceTextView = (TextView)findViewById(R.id.matchDetailsRedAllianceWinChance);

        redAllianceScoreTextView.setText(match.redScore.toString());
        redAlliancePredictedScoreTextView.setText(Utils.roundDataPoint(match.calculatedData.predictedRedScore, 0));
        redAllianceWinChanceTextView.setText(Utils.dataPointToPercentage(match.calculatedData.redWinChance, 0));

        TextView blueAllianceScoreTextView = (TextView)findViewById(R.id.matchDetailsBlueAllianceScore);
        TextView blueAlliancePredictedScoreTextView = (TextView)findViewById(R.id.matchDetailsBlueAlliancePredictedScore);
        TextView blueAllianceWinChanceTextView = (TextView)findViewById(R.id.matchDetailsBlueAllianceWinChance);

        blueAllianceScoreTextView.setText(match.blueScore.toString());
        blueAlliancePredictedScoreTextView.setText(Utils.roundDataPoint(match.calculatedData.predictedBlueScore, 0));
        blueAllianceWinChanceTextView.setText(Utils.dataPointToPercentage(match.calculatedData.blueWinChance, 0));

        LinearLayout redOptimalDefensesLinearLayout = (LinearLayout)findViewById(R.id.matchDetailsRedTeamsDefensesLinearLayout);
        for (String redOptimalDefense : match.calculatedData.optimalRedDefenses) {
            redOptimalDefensesLinearLayout.addView(getTeamInMatchDefenseCell(redOptimalDefense, true));
        }

        LinearLayout blueOptimalDefensesLinearLayout = (LinearLayout)findViewById(R.id.matchDetailsBlueTeamsDefensesLinearLayout);
        for (String blueOptimalDefense : match.calculatedData.optimalBlueDefenses) {
            blueOptimalDefensesLinearLayout.addView(getTeamInMatchDefenseCell(blueOptimalDefense, false));
        }
    }

    private class MatchDetailsTeamClickedListener implements ViewGroup.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.e("test", "CLICKED!");
            TextView teamNumberTextView = (TextView)v.findViewById(R.id.matchDetailsTeamCellTeamNumberTextView);
            String teamNumberText = teamNumberTextView.getText().toString();
            Integer teamNumber = Integer.parseInt(teamNumberText);
            Intent matchDetailsTeamCellClickedIntent = new Intent(getApplicationContext(), TeamInMatchDetailsActivity.class);
            matchDetailsTeamCellClickedIntent.putExtra("teamNumber", teamNumber);
            matchDetailsTeamCellClickedIntent.putExtra("matchNumber", match.number);
            startActivity(matchDetailsTeamCellClickedIntent);
        }
    }

    private View getTeamInMatchCell(Integer teamNumber, boolean isRed) {
        View view = getLayoutInflater().inflate(R.layout.match_details_team_cell, null);
        TextView teamNumberTextView = (TextView)view.findViewById(R.id.matchDetailsTeamCellTeamNumberTextView);
        teamNumberTextView.setText(teamNumber.toString());
        teamNumberTextView.setTextColor((isRed) ? Color.RED : Color.BLUE);
        teamNumberTextView.setOnClickListener(new MatchDetailsTeamClickedListener());

        ListView listView = (ListView)view.findViewById(R.id.matchDetailsTeamCellTeamValues);
        listView.setAdapter(new MatchDetailsTeamCellAdapter(getApplicationContext(), teamNumber));

        return view;
    }

    private View getTeamInMatchDefenseCell(String defense, boolean isRed) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        TextView optimalDefenseView = new TextView(getApplicationContext());
        optimalDefenseView.setText(defense.toUpperCase());
        optimalDefenseView.setTextColor((isRed) ? Color.RED : Color.BLUE);
        optimalDefenseView.setGravity(Gravity.CENTER);
        optimalDefenseView.setLayoutParams(params);
        return optimalDefenseView;
    }

}
