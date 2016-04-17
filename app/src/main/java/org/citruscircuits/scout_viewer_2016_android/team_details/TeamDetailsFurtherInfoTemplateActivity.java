package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerActivity;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;

public abstract class TeamDetailsFurtherInfoTemplateActivity extends ViewerActivity {
    Integer teamNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_listview);

        teamNumber = getIntent().getIntExtra("teamNumber", 1678);

        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(getAdapter());
        View teamDetailsHeaderView = getLayoutInflater().inflate(R.layout.team_details_header, null);
        teamDetailsHeaderListView.getListView().addHeaderView(teamDetailsHeaderView, null, false);

        reload();
    }
    public void reload() {
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        View teamDetailsHeaderView = teamDetailsHeaderListView.getChildAt(0);
        if (StarManager.isStarredTeam(teamNumber)) {
            teamDetailsHeaderView.setBackgroundColor(Constants.STAR_COLOR);
        } else {
            teamDetailsHeaderView.setBackgroundColor(Color.TRANSPARENT);
        }

        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());

        TextView teamDetailsTeamNumberTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNumberTextView);
        teamDetailsTeamNumberTextView.setText(Utils.getDisplayValue(team, "number"));
        teamDetailsTeamNumberTextView.setOnLongClickListener(new StarLongClickListener());

        TextView teamDetailsTeamNameTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNameTextView);
        teamDetailsTeamNameTextView.setText(Utils.getDisplayValue(team, "name"));

        TextView teamDetailsSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeeding);
        teamDetailsSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.actualSeed")) ? Utils.roundDataPoint(team.calculatedData.actualSeed, 2, "???") : "???");

        TextView teamDetailsPredictedSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);
        teamDetailsPredictedSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.predictedSeed")) ? Utils.roundDataPoint(team.calculatedData.predictedSeed, 2, "???") : "???");
    }

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView teamNumberTextView = (TextView) v;
            Integer teamNumber = Integer.parseInt(teamNumberTextView.getText().toString());
            if (StarManager.isStarredTeam(teamNumber)) {
                StarManager.removeStarredTeam(teamNumber);
            } else {
                StarManager.addStarredTeam(teamNumber);
            }
            reload();
            return true;
        }
    }

    protected abstract MultitypeRankingsSectionAdapter getAdapter();
}
