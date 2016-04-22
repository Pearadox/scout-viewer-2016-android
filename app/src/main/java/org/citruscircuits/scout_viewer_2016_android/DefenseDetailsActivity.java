package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.SearchableFirebaseListAdapter;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamRankingsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by colinunger on 2/13/16.
 */
public class DefenseDetailsActivity extends ViewerActivity {
    Integer teamNumber;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_section_listview);
        setTitle(Constants.KEYS_TO_TITLES.get(getIntent().getStringExtra("defense")));
        teamNumber = getIntent().getIntExtra("teamNumber", 1678);
        Log.i("teamNumber", teamNumber.toString());

        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new DefenseDetailsSectionAdapter(this, teamNumber, getIntent().getStringExtra("defense")));
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


    private class DefenseDetailsSectionAdapter extends MultitypeRankingsSectionAdapter {
        Integer teamNumber;
        String defense;
        public DefenseDetailsSectionAdapter(Context context, Integer teamNumber, String defense) {
            super(context);
            this.teamNumber = teamNumber;
            this.defense = defense;
            String[] overallFields = fieldsToDisplay[0];
            if (defense.equals("pc") || defense.equals("cdf")) {
                String[] newOverallFields = Arrays.copyOf(overallFields, overallFields.length + 2);
                newOverallFields[overallFields.length] = "calculatedData.slowedPercentage.DEFENSE";
                newOverallFields[overallFields.length+1] = "calculatedData.beachedPercentage.DEFENSE";
                fieldsToDisplay[0] = newOverallFields;
            }
            for (int i = 0; i < fieldsToDisplay.length; i++) {
                fieldsToDisplay[i] = formatStringList(fieldsToDisplay[i]);
            }
            sectionTitles = formatStringList(sectionTitles);
            shouldDisplayAsPercentage = formatStringList(shouldDisplayAsPercentage);
            createListOnClick = formatStringList(createListOnClick);
        }


        private String[] formatStringList(String[] list) {
            List<String> newList = new ArrayList<>();
            for (String dataPoint : Arrays.asList(list)) {
                newList.add(dataPoint.replaceAll("DEFENSE", defense));
            }
            return newList.toArray(new String[list.length]);
        }


        private String[][] fieldsToDisplay = {
                {"calculatedData.avgSuccessfulTimesCrossedDefenses.DEFENSE"},
                {"calculatedData.avgSuccessfulTimesCrossedDefensesAuto.DEFENSE",
                        "calculatedData.avgFailedTimesCrossedDefensesAuto.DEFENSE",
                        "calculatedData.avgTimeForDefenseCrossAuto.DEFENSE",
                        "calculatedData.crossingsSuccessRateForDefenseAuto.DEFENSE",
                        "calculatedData.sdSuccessfulDefenseCrossesAuto.DEFENSE",
                        "calculatedData.sdFailedDefenseCrossesAuto.DEFENSE"},
                {"calculatedData.avgSuccessfulTimesCrossedDefensesTele.DEFENSE",
                        "calculatedData.avgFailedTimesCrossedDefensesTele.DEFENSE",
                        "calculatedData.avgTimeForDefenseCrossTele.DEFENSE",
                        "calculatedData.predictedSuccessfulCrossingsForDefenseTele.DEFENSE",
                        "calculatedData.crossingsSuccessRateForDefenseTele.DEFENSE",
                        "calculatedData.sdSuccessfulDefenseCrossesTele.DEFENSE",
                        "calculatedData.sdFailedDefenseCrossesTele.DEFENSE"}
        };


        private String[] sectionTitles = {"Overall", "Auto", "Teleop"};


        private String[] shouldDisplayAsPercentage = {"calculatedData.crossingsSuccessRateForDefenseAuto.DEFENSE",
                "calculatedData.crossingsSuccessRateForDefenseTele.DEFENSE",
                "calculatedData.slowedPercentage.DEFENSE",
                "calculatedData.beachedPercentage.DEFENSE"};


        private String[] createListOnClick = {"calculatedData.avgSuccessfulTimesCrossedDefenses.DEFENSE",
                "calculatedData.predictedSuccessfulCrossingsForDefenseTele.DEFENSE",
                "calculatedData.sdSuccessfulDefenseCrossesTele.DEFENSE",
                "calculatedData.sdFailedDefenseCrossesTele.DEFENSE",
                "calculatedData.sdSuccessfulDefenseCrossesAuto.DEFENSE",
                "calculatedData.sdFailedDefenseCrossesAuto.DEFENSE"};


        @Override
        public String[][] getFieldsToDisplay() {
            return fieldsToDisplay;
        }

        @Override
        public String[] getSectionTitles() {
            return sectionTitles;
        }

        @Override
        public String[] getUnrankedFields() {
            return new String[]{};
        }

        @Override
        public String[] getLongTextFields() {
            return new String[]{};
        }

        @Override
        public String[] getPercentageFields() {
            return shouldDisplayAsPercentage;
        }

        @Override
        public String[] getFurtherInformationFields() {
            return new String[]{};
        }

        @Override
        public String[] getNotClickableFields() {
            return new String[]{};
        }

        @Override
        public String[] getNonDefaultClickResponseFields() {
            return createListOnClick;
        }


        @Override
        public void handleNonDefaultClick(int section, int row) {
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row));
            context.startActivity(intent);
        }

        @Override
        public String getUpdatedAction() {
            return Constants.TEAMS_UPDATED_ACTION;
        }

        @Override
        public Object getObject() {
            return FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        }

        @Override
        public List<Object> getObjectList() {
            List<Object> teams = new ArrayList<>();
            teams.addAll(FirebaseLists.teamsList.getValues());
            return teams;
        }

        @Override
        public boolean isOtherTypeOfView(int section, int row) {
            return false;
        }

        @Override
        public boolean onRowItemLongClick (AdapterView<?> parent, View view, int section, int row, long id) {
            if (!isUnranked(section, row)) {
                Intent intent = new Intent(context, TeamRankingsActivity.class);
                intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row));
                context.startActivity(intent);
            }
            return true;
        }
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
}
