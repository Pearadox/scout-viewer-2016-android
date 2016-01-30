package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
        for (Integer teamNumber : match.redAllianceTeamNumbers) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            View view = getLayoutInflater().inflate(R.layout.match_details_team_cell, redTeamsLinearLayout);
            TextView teamNumberTextView = (TextView)view.findViewById(R.id.matchDetailsTeamCellTeamNumberTextView);
            teamNumberTextView.setText(teamNumber.toString());
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.RED);
        }
    }

}
