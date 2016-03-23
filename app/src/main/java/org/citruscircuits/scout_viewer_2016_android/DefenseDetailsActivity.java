package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.MatchesAdapter;
import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes.SearchableFirebaseListAdapter;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamRankingsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 2/13/16.
 */
public class DefenseDetailsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_listview);
        setTitle(Constants.KEYS_TO_TITLES.get(getIntent().getStringExtra("defense")));
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new DefenseDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678), getIntent().getStringExtra("defense")));
        /*setContentView(R.layout.activity_rankings);
        String defense = getIntent().getStringExtra("defense");
        setTitle(Constants.KEYS_TO_TITLES.get(defense));

        FragmentManager fragmentManager = getSupportFragmentManager();
        DefenseDetailsActivityFragment fragment = new DefenseDetailsActivityFragment();

        Bundle args = new Bundle();
        args.putString("defense", defense);
        args.putInt("teamNumber", getIntent().getIntExtra("teamNumber", 0));

        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.rankingsLinearLayout, fragment, "").commit();*/
    }
    private class DefenseDetailsSectionAdapter extends MultitypeRankingsSectionAdapter {
        Integer teamNumber;
        String defense;
        public DefenseDetailsSectionAdapter(Context context, Integer teamNumber, String defense) {
            super(context);
            this.teamNumber = teamNumber;
            this.defense = defense;
            for (int i = 0; i < fieldsToDisplay.length; i++) {
                for (int j = 0; j < fieldsToDisplay[i].length; j++) {
                    fieldsToDisplay[i][j] = fieldsToDisplay[i][j].replaceAll("DEFENSE", defense);
                }
            }
            for (int i = 0; i < sectionTitles.length; i++) {
                sectionTitles[i] = sectionTitles[i].replaceAll("DEFENSE", defense);
            }
            for (int i = 0; i < shouldDisplayAsPercentage.length; i++) {
                shouldDisplayAsPercentage[i] = shouldDisplayAsPercentage[i].replaceAll("DEFENSE", defense);
            }
            for (int i = 0; i < createListOnClick.length; i++) {
                createListOnClick[i] = createListOnClick[i].replaceAll("DEFENSE", defense);
            }
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
                "calculatedData.crossingsSuccessRateForDefenseTele.DEFENSE"};


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
}
