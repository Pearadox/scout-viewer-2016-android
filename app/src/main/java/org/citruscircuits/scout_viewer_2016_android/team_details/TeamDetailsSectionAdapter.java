package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.RankingsActivity;
import org.citruscircuits.scout_viewer_2016_android.RankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class TeamDetailsSectionAdapter extends MultitypeRankingsSectionAdapter {
    private String[][] fieldsToDisplay = {
            {"calculatedData.firstPickAbility"},
            {"calculatedData.numAutoPoints",
                    "calculatedData.highShotAccuracyAuto",
                    "calculatedData.lowShotAccuracyAuto",
                    "calculatedData.avgMidlineBallsIntakedAuto",
                    "calculatedData.avgBallsKnockedOffMidlineAuto",
                    "calculatedData.avgHighShotsAuto"},
            {"calculatedData.highShotAccuracyTele",
                    "calculatedData.lowShotAccuracyTele",
                    "calculatedData.avgHighShotsTele",
                    "calculatedData.avgLowShotsTele",
                    "calculatedData.avgGroundIntakes"},
            {},
            {"calculatedData.siegeConsistency"},
            {"calculatedData.disabledPercentage",
                    "calculatedData.incapacitatedPercentage"},
            {"pitLowBarCapability",
                    "pitPotentialLowBarCapability",
                    "pitPotentialMidlineBallCapability",
                    "pitDriveBaseWidth",
                    "pitDriveBaseLength",
                    "pitBumperHeight",
                    "pitPotentialShotBlockerCapability",
                    "pitOrganization",
                    "pitNotes"}};

    private String[] sectionTitles = {"High Level", "Auto", "Teleop", "Defenses", "Siege", "Status", "Pit"};

    private String[] shouldDisplayAsPercentage = {
            "calculatedData.highShotAccuracyTele",
            "calculatedData.lowShotAccuracyTele",
            "calculatedData.highShotAccuracyAuto",
            "calculatedData.lowShotAccuracyAuto",
            "calculatedData.siegeConsistency",
            "calculatedData.disabledPercentage",
            "calculatedData.incapacitatedPercentage"
    };

    private String[] displayAsUnranked = {
            "pitLowBarCapability",
            "pitPotentialLowBarCapability",
            "pitPotentialMidlineBallCapability",
            "pitDriveBaseWidth",
            "pitDriveBaseLength",
            "pitBumperHeight",
            "pitPotentialShotBlockerCapability",
            "pitOrganization"
    };

    private String[] shouldDisplayAsLongText = {
            "pitNotes"
    };

    Integer teamNumber;

    public TeamDetailsSectionAdapter(Context context, Integer teamNumber) {
        super(context);
        this.teamNumber = teamNumber;
    }

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
        return displayAsUnranked;
    }

    @Override
    public String[] getLongTextFields() {
        return shouldDisplayAsLongText;
    }

    @Override
    public String[] getPercentageFields() {
        return shouldDisplayAsPercentage;
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
}