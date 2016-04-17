package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.R;

public class SuperDetailsFurtherInfo extends TeamDetailsFurtherInfoTemplateActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Super Details");
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new SuperDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678)));
    }

    @Override
    protected MultitypeRankingsSectionAdapter getAdapter() {
        return new SuperDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678));
    }

    private class SuperDetailsSectionAdapter extends TeamDetailsFurtherInfoSectionAdapter {
        public SuperDetailsSectionAdapter (Context context, Integer teamNumber) {
            super(context, teamNumber);
        }
        private String[][] fieldsToDisplay = {
                {"calculatedData.RScoreDrivingAbility",
                        "calculatedData.RScoreSpeed",
                        "calculatedData.RScoreTorque",
                        "calculatedData.RScoreAgility",
                        "calculatedData.RScoreDefense",
                        "calculatedData.RScoreBallControl"}
        };
        private String[] sectionTitles = {
                "Super"
        };
        private String[] shouldDisplayAsPercentage = {};
        private String[] createListOnClick = {"calculatedData.RScoreDrivingAbility"};
        @Override
        public String[][] getFieldsToDisplay() {return fieldsToDisplay;}

        @Override
        public String[] getSectionTitles() {return sectionTitles;}

        @Override
        public String[] getPercentageFields() {return shouldDisplayAsPercentage;}

        @Override
        public String[] getNonDefaultClickResponseFields() {return createListOnClick;}
    }
}
