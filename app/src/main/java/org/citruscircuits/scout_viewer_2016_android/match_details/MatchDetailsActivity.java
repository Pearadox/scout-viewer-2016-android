package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseList;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerActivity;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MatchDetailsActivity extends ViewerActivity {
    private Integer matchNumber;
    private BroadcastReceiver matchesUpdatedReceiver;
    private BroadcastReceiver starReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        matchNumber = getIntent().getIntExtra("matchNumber", 0);

        matchesUpdatedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUI();
            }
        };

        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUI();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(starReceiver, new IntentFilter(Constants.STARS_MODIFIED_ACTION));

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(matchesUpdatedReceiver, new IntentFilter(Constants.MATCHES_UPDATED_ACTION));
        TextView matchNumberTextView = (TextView)findViewById(R.id.matchDetailsMatchTitleTextView);
        matchNumberTextView.setOnLongClickListener(new StarLongClickListener());
        updateUI();
    }

    private void updateUI() {
        Match match = FirebaseLists.matchesList.getFirebaseObjectByKey(matchNumber.toString());
        int[] teamCellIDs = {R.id.redTeamCell1, R.id.redTeamCell2, R.id.redTeamCell3, R.id.blueTeamCell1, R.id.blueTeamCell2, R.id.blueTeamCell3};
        List<Integer> allTeamNumbers = new ArrayList<>();
        allTeamNumbers.addAll(match.redAllianceTeamNumbers);
        allTeamNumbers.addAll(match.blueAllianceTeamNumbers);
        for (int i = 0; i < teamCellIDs.length; i++) {
            MatchDetailsTeamCell matchDetailsTeamCell = (MatchDetailsTeamCell)findViewById(teamCellIDs[i]);
            matchDetailsTeamCell.update(allTeamNumbers.get(i), (i < 3));
        }

        if (StarManager.isImportantMatch(matchNumber)) {
            getWindow().getDecorView().setBackgroundColor(Constants.STAR_COLOR);
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }

        TextView matchDetailsMatchTitleTextView = (TextView)findViewById(R.id.matchDetailsMatchTitleTextView);
        matchDetailsMatchTitleTextView.setText("Q" + match.number.toString());

        TextView redAllianceScoreTextView = (TextView)findViewById(R.id.matchDetailsRedAllianceScore);
        TextView redAlliancePredictedScoreTextView = (TextView)findViewById(R.id.matchDetailsRedAlliancePredictedScore);
        TextView redAllianceWinChanceTextView = (TextView)findViewById(R.id.matchDetailsRedAllianceWinChance);

        redAllianceScoreTextView.setText(Utils.getMatchDisplayValue(match, "redScore"));
        redAlliancePredictedScoreTextView.setText(Utils.getMatchDisplayValue(match, "calculatedData.predictedRedScore"));
        redAllianceWinChanceTextView.setText(Utils.dataPointToPercentage((Float)Utils.getObjectField(match, "calculatedData.redWinChance"), 0));

        TextView blueAllianceScoreTextView = (TextView)findViewById(R.id.matchDetailsBlueAllianceScore);
        TextView blueAlliancePredictedScoreTextView = (TextView)findViewById(R.id.matchDetailsBlueAlliancePredictedScore);
        TextView blueAllianceWinChanceTextView = (TextView)findViewById(R.id.matchDetailsBlueAllianceWinChance);

        blueAllianceScoreTextView.setText(Utils.getMatchDisplayValue(match, "blueScore"));
        blueAlliancePredictedScoreTextView.setText(Utils.getMatchDisplayValue(match, "calculatedData.predictedBlueScore"));
        blueAllianceWinChanceTextView.setText(Utils.dataPointToPercentage((Float) Utils.getObjectField(match, "calculatedData.blueWinChance"), 0));

        LinearLayout redOptimalDefensesLinearLayout = (LinearLayout)findViewById(R.id.matchDetailsRedTeamsDefensesLinearLayout);
        redOptimalDefensesLinearLayout.removeAllViews();
        if (Utils.getObjectField(match, "calculatedData.optimalRedDefenses") != null) {
            for (String redOptimalDefense : match.calculatedData.optimalRedDefenses) {
                redOptimalDefensesLinearLayout.addView(getTeamInMatchDefenseCell(redOptimalDefense, true));
            }
        }

        LinearLayout blueOptimalDefensesLinearLayout = (LinearLayout)findViewById(R.id.matchDetailsBlueTeamsDefensesLinearLayout);
        blueOptimalDefensesLinearLayout.removeAllViews();
        if (Utils.getObjectField(match, "calculatedData.optimalBlueDefenses") != null) {
            for (String blueOptimalDefense : match.calculatedData.optimalBlueDefenses) {
                blueOptimalDefensesLinearLayout.addView(getTeamInMatchDefenseCell(blueOptimalDefense, false));
            }
        }
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

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView matchNumberTextView = (TextView)v;
            String matchNumberString = matchNumberTextView.getText().toString().substring(1);
            Integer matchNumberClicked = Integer.parseInt(matchNumberString);
            if (StarManager.isImportantMatch(matchNumberClicked)) {
                StarManager.removeImportantMatch(matchNumberClicked);
            } else {
                StarManager.addImportantMatch(matchNumberClicked);
            }

            updateUI();
            return true;
        }
    }
}
