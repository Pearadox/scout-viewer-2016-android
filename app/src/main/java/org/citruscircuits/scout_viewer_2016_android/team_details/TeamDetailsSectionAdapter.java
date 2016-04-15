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
                    "calculatedData.lowShotAccuracyAuto",
                    "calculatedData.avgMidlineBallsIntakedAuto",
                    "calculatedData.avgBallsKnockedOffMidlineAuto",
                    "calculatedData.avgHighShotsAuto",
                    "calculatedData.twoBallAutoAccuracy",
                    "calculatedData.twoBallAutoAttemptedPercentage",
                    "calculatedData.sdHighShotsAuto",
                    "calculatedData.sdLowShotsAuto"},
            {"calculatedData.highShotAccuracyTele",
                    "calculatedData.lowShotAccuracyTele",
                    "calculatedData.avgHighShotsTele",
                    "calculatedData.avgLowShotsTele",
                    "calculatedData.avgGroundIntakes",
                    "calculatedData.sdHighShotsTele",
                    "calculatedData.sdLowShotsTele"},
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
            {"calculatedData.RScoreDrivingAbility",
                    "calculatedData.RScoreSpeed",
                    "calculatedData.RScoreTorque",
                    "calculatedData.RScoreAgility",
                    "calculatedData.RScoreDefense",
                    "calculatedData.RScoreBallControl"},
            {"pitOrganization",
                    "pitCheesecakeAbility",
                    "pitAvailableWeight",
                    "pitNotes"}};

    private String[] sectionTitles = {"Matches", "High Level", "Auto", "Teleop", "Defenses", "Siege", "Status", "Super", "Pit"};

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
            "pitOrganization",
            "VIEWER.matchesUntilNextMatchForTeam",
            "pitCheesecakeAbility",
            "pitAvailableWeight",
    };

    private String[] shouldDisplayAsLongText = {
            "pitNotes"
    };

    private String[] shouldDisplayAsFurtherInformation = {
            "matches"
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
            "calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",
            "calculatedData.twoBallAutoAccuracy",
            "calculatedData.twoBallAutoAttemptedPercentage",
            "calculatedData.sdHighShotsTele",
            "calculatedData.sdLowShotsTele",
            "calculatedData.sdHighShotsAuto",
            "calculatedData.sdLowShotsAuto"
    };

    private String[] rankInsteadOfGraph = {
            "calculatedData.firstPickAbility",
            "calculatedData.overallSecondPickAbility",
            "calculatedData.twoBallAutoAccuracy",
            "calculatedData.twoBallAutoAttemptedPercentage",
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
        } else if (Arrays.asList(rankInsteadOfGraph).contains(key)) {
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row));
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
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row))
                    .putExtra("displayValueAsPercentage", Arrays.asList(getPercentageFields()).contains(getRowItem(section,row)));
            context.startActivity(intent);
        }
        return true;
    }
}