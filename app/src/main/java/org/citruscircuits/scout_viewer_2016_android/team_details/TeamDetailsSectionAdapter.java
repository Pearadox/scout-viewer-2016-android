package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.DefenseDetailsActivity;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MatchesActivity;
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
            {"matches",
            "VIEWER.matchesUntilNextMatchForTeam"},
            {"calculatedData.firstPickAbility",
                    "calculatedData.overallSecondPickAbility"},
            {"calculatedData.numAutoPoints",
                    "calculatedData.highShotAccuracyAuto",
                    "calculatedData.avgHighShotsAuto",
                    "calculatedData.twoBallAutoAccuracy",
                    "calculatedData.avgNumTimesCrossedDefensesAuto",
                    "autoDetails"},
            {"calculatedData.highShotAccuracyTele",
                    "calculatedData.lowShotAccuracyTele",
                    "calculatedData.avgHighShotsTele",
                    "calculatedData.avgLowShotsTele",
                    "teleDetails"},
            {"VIEWER.defenseCrossingTeamDetailsTitle.pc",
                    "VIEWER.defenseCrossingTeamDetailsTitle.cdf",
                    "VIEWER.defenseCrossingTeamDetailsTitle.mt",
                    "VIEWER.defenseCrossingTeamDetailsTitle.rp",
                    "VIEWER.defenseCrossingTeamDetailsTitle.db",
                    "VIEWER.defenseCrossingTeamDetailsTitle.sp",
                    "VIEWER.defenseCrossingTeamDetailsTitle.rw",
                    "VIEWER.defenseCrossingTeamDetailsTitle.rt",
                    "VIEWER.defenseCrossingTeamDetailsTitle.lb"},
            {"calculatedData.siegeConsistency"},
            {"calculatedData.disabledPercentage",
                    "calculatedData.incapacitatedPercentage"},
            {"superDetails",
                    "pitDetails"}};

    private String[] sectionTitles = {"Matches", "High Level", "Auto", "Teleop", "Defenses", "Siege", "Status", "More Info"};

    private String[] shouldDisplayAsPercentage = {
            "calculatedData.highShotAccuracyTele",
            "calculatedData.lowShotAccuracyTele",
            "calculatedData.highShotAccuracyAuto",
            "calculatedData.lowShotAccuracyAuto",
            "calculatedData.siegeConsistency",
            "calculatedData.disabledPercentage",
            "calculatedData.incapacitatedPercentage",
            "calculatedData.twoBallAutoAccuracy",
            "calculatedData.twoBallAutoTriedPercentage"
    };

    private String[] displayAsUnranked = {
            "matches",
            "autoDetails",
            "teleDetails",
            "superDetails",
            "pitDetails",
            "pitLowBarCapability",
            "pitPotentialLowBarCapability",
            "pitPotentialMidlineBallCapability",
            "pitDriveBaseWidth",
            "pitDriveBaseLength",
            "pitBumperHeight",
            "pitPotentialShotBlockerCapability",
            "pitOrganization",
            "VIEWER.matchesUntilNextMatchForTeam",
            "pitCheesecakeAbility",
            "pitAvailableWeight",
    };

    private String[] shouldDisplayAsLongText = {
            "pitNotes"
    };

    private String[] shouldDisplayAsFurtherInformation = {
            "autoDetails",
            "matches",
            "pitDetails",
            "teleDetails",
            "superDetails"
    };

    private String[] notClickableFields = {
            "pitLowBarCapability",
            "pitPotentialLowBarCapability",
            "pitPotentialMidlineBallCapability",
            "pitDriveBaseWidth",
            "pitDriveBaseLength",
            "pitBumperHeight",
            "pitPotentialShotBlockerCapability",
            "pitOrganization",
            "pitNotes",
            "pitCheesecakeAbility",
            "pitAvailableWeight",
            "VIEWER.matchesUntilNextMatchForTeam"
    };

    private String[] createListOnClick = {
            "VIEWER.defenseCrossingTeamDetailsTitle.pc",
            "VIEWER.defenseCrossingTeamDetailsTitle.cdf",
            "VIEWER.defenseCrossingTeamDetailsTitle.mt",
            "VIEWER.defenseCrossingTeamDetailsTitle.rp",
            "VIEWER.defenseCrossingTeamDetailsTitle.db",
            "VIEWER.defenseCrossingTeamDetailsTitle.sp",
            "VIEWER.defenseCrossingTeamDetailsTitle.rw",
            "VIEWER.defenseCrossingTeamDetailsTitle.rt",
            "VIEWER.defenseCrossingTeamDetailsTitle.lb",
            "matches",
            "autoDetails",
            "teleDetails",
            "pitDetails",
            "superDetails",
            "calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",
            "calculatedData.twoBallAutoAccuracy",
            "calculatedData.twoBallAutoTriedPercentage",
            "calculatedData.sdHighShotsTele",
            "calculatedData.sdLowShotsTele",
            "calculatedData.sdHighShotsAuto",
            "calculatedData.sdLowShotsAuto"
    };

    private String[] rankInsteadOfGraph = {
            "calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",
            "calculatedData.twoBallAutoAccuracy",
            "calculatedData.twoBallAutoTriedPercentage",
            "calculatedData.sdHighShotsTele",
            "calculatedData.sdLowShotsTele",
            "calculatedData.sdHighShotsAuto",
            "calculatedData.sdLowShotsAuto"
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
    public String[] getFurtherInformationFields() {
        return shouldDisplayAsFurtherInformation;
    }

    @Override
    public String[] getNotClickableFields() {
        return notClickableFields;
    }

    @Override
    public String[] getNonDefaultClickResponseFields() {
        return createListOnClick;
    }


    @Override
    public void handleNonDefaultClick(int section, int row) {
        String key = (String)getRowItem(section, row);
        if (key == "matches") {
            Intent teamMatchesIntent = new Intent(context, MatchesActivity.class);
            teamMatchesIntent.putExtra("teamNumber", teamNumber).putExtra("field", "matches");
            context.startActivity(teamMatchesIntent);
        } else if (key.equals("autoDetails")) {
            Intent intent = new Intent(context, AutoDetailsFurtherInfo.class);
            intent.putExtra("teamNumber", teamNumber);
            context.startActivity(intent);
        } else if (key.equals("teleDetails")) {
            Intent intent = new Intent(context, TeleDetailsFurtherInfo.class);
            intent.putExtra("teamNumber", teamNumber);
            context.startActivity(intent);
        } else if (key.equals("superDetails")) {
            Intent intent = new Intent(context, SuperDetailsFurtherInfo.class);
            intent.putExtra("teamNumber", teamNumber);
            context.startActivity(intent);
        } else if (key.equals("pitDetails")) {
            Intent intent = new Intent(context, PitDetailsFurtherInfo.class);
            intent.putExtra("teamNumber", teamNumber);
            context.startActivity(intent);
        } else if (Arrays.asList(rankInsteadOfGraph).contains(key)) {
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row))
                .putExtra("displayValueAsPercentage", Arrays.asList(shouldDisplayAsPercentage).contains(key));
            context.startActivity(intent);
        } else {
            Intent rankingsActivityIntent = new Intent(context, DefenseDetailsActivity.class);
            String defenseKey = (String)getRowItem(section, row);
            String[] splitDefenseKey = defenseKey.split("\\.");
            rankingsActivityIntent.putExtra("defense", /*splitDefenseKey[splitDefenseKey.length - 2] + "." + */splitDefenseKey[splitDefenseKey.length - 1]);
            Log.i("QSDFE", splitDefenseKey[splitDefenseKey.length - 2] + "." + splitDefenseKey[splitDefenseKey.length - 1]);
            rankingsActivityIntent.putExtra("teamNumber", teamNumber);
            context.startActivity(rankingsActivityIntent);
        }
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
        return (Arrays.asList(shouldDisplayAsLongText).contains(getRowItem(section, row)) ||
                Arrays.asList(shouldDisplayAsFurtherInformation).contains(getRowItem(section, row)));
    }

    @Override
    public boolean onRowItemLongClick (AdapterView<?> parent, View view, int section, int row, long id) {
        if (!isUnranked(section, row)) {
            String fieldName = (String)getRowItem(section,row);
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            if (fieldName.startsWith("VIEWER.")) {
                List<String> datas = Arrays.asList(fieldName.replaceFirst("VIEWER.", "").split("\\."));
                String ending = datas.get(datas.size() - 1);
                Intent rankDataArgs = new Intent();
                if (Constants.DEFENSE_ENDINGS.contains(ending)) {
                    rankDataArgs.putExtra("defense", ending);
                    fieldName = fieldName.replaceAll("." + ending, "");
                }
                fieldName = Utils.getViewerObjectFieldRank(fieldName.replaceFirst("VIEWER.", ""), rankDataArgs);
            }
            intent.putExtra("teamNumber", teamNumber).putExtra("field", fieldName)
                    .putExtra("displayValueAsPercentage", Arrays.asList(getPercentageFields()).contains(getRowItem(section,row)));
            context.startActivity(intent);
        }
        return true;
    }
}