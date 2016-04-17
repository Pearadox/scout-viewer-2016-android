package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.R;

import java.util.ArrayList;
import java.util.List;

public class PitDetailsFurtherInfo extends TeamDetailsFurtherInfoTemplateActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pit Details");
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new PitDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678)));
    }

    @Override
    protected MultitypeRankingsSectionAdapter getAdapter() {
        return new PitDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678));
    }

    private class PitDetailsSectionAdapter extends MultitypeRankingsSectionAdapter {
        Integer teamNumber;
        public PitDetailsSectionAdapter (Context context, Integer teamNumber) {
            super(context);
            this.teamNumber = teamNumber;
        }
        private String[][] fieldsToDisplay = {
                {"pitOrganization",
                        "pitCheesecakeAbility",
                        "pitAvailableWeight",
                        "pitProgrammingLanguage",
                        "pitNotes"}
        };
        private String[] sectionTitles = {"Pit"};
        private String[] shouldDisplayPercentage = {};
        private String[] createListOnClick = {};
        private String[] shouldDisplayAsLongText = {
                "pitNotes",
                "pitProgrammingLanguage"
        };
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
            return fieldsToDisplay[0];
        }

        @Override
        public String[] getLongTextFields() {
            return shouldDisplayAsLongText;
        }

        @Override
        public String[] getPercentageFields() {
            return shouldDisplayPercentage;
        }

        @Override
        public String[] getFurtherInformationFields() {
            return new String[]{};
        }

        @Override
        public String[] getNotClickableFields() {
            return fieldsToDisplay[0];
        }

        @Override
        public String[] getNonDefaultClickResponseFields() {
            return createListOnClick;
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
        public void handleNonDefaultClick(int section, int row) {}
    }
}
