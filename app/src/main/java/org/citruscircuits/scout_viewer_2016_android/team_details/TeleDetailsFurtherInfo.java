package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.R;

public class TeleDetailsFurtherInfo extends TeamDetailsFurtherInfoTemplateActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Teleop Details");
    }

    @Override
    protected MultitypeRankingsSectionAdapter getAdapter() {
        return new TeleDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678));
    }

    private class TeleDetailsSectionAdapter extends TeamDetailsFurtherInfoSectionAdapter {
        public TeleDetailsSectionAdapter (Context context, Integer teamNumber) {
            super(context, teamNumber);
        }
        private String[][] fieldsToDisplay = {
                {"calculatedData.avgGroundIntakes",
                        "calculatedData.sdHighShotsTele",
                        "calculatedData.sdLowShotsTele"}
        };
        private String[] sectionTitles = {
                "Teleop"
        };
        private String[] shouldDisplayAsPercentage = {};
        private String[] createListOnClick = {
                "calculatedData.sdHighShotsTele",
                "calculatedData.sdLowShotsTele"
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
        public String[] getPercentageFields() {
            return shouldDisplayAsPercentage;
        }

        @Override
        public String[] getNonDefaultClickResponseFields() {
            return createListOnClick;
        }
    }
}
