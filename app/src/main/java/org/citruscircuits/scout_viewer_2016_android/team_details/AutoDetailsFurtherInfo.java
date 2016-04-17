package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.ViewerActivity;

import java.util.ArrayList;
import java.util.List;

public class AutoDetailsFurtherInfo extends TeamDetailsFurtherInfoTemplateActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Auto Details");
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new AutoDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678)));
    }

    @Override
    protected MultitypeRankingsSectionAdapter getAdapter() {
        return new AutoDetailsSectionAdapter(this, getIntent().getIntExtra("teamNumber", 1678));
    }

    private class AutoDetailsSectionAdapter extends TeamDetailsFurtherInfoSectionAdapter {
        public AutoDetailsSectionAdapter (Context context, Integer teamNumber) {
            super(context, teamNumber);
        }
        private String[][] fieldsToDisplay = {
                {"calculatedData.autoAbilityExcludeD",
                        "calculatedData.autoAbilityExcludeLB",
                        "calculatedData.lowShotAccuracyAuto",
                        "calculatedData.avgMidlineBallsIntakedAuto",
                        "calculatedData.avgBallsKnockedOffMidlineAuto",
                        "calculatedData.twoBallAutoTriedPercentage",
                        "calculatedData.sdHighShotsAuto",
                        "calculatedData.sdLowShotsAuto",
                        "calculatedData.defensesCrossableAuto"}
        };
        private String[] sectionTitles = {
                "Auto"
        };
        private String[] shouldDisplayAsPercentage = {
                "calculatedData.lowShotAccuracyAuto",
                "calculatedData.twoBallAutoTriedPercentage"
        };
        private String[] createListOnClick = {
                "calculatedData.autoAbilityExcludeD",
                "calculatedData.autoAbilityExcludeLB",
                "calculatedData.twoBallAutoTriedPercentage",
                "calculatedData.sdHighShotsAuto",
                "calculatedData.sdLowShotsAuto"
        };
        private String[] longTextFields = {
                "calculatedData.defensesCrossableAuto"
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

        @Override
        public String[] getLongTextFields() {return longTextFields;}

        @Override
        public String[] getNotClickableFields() {return longTextFields;}

        @Override
        public String[] getUnrankedFields() {return longTextFields;}
    }
}
