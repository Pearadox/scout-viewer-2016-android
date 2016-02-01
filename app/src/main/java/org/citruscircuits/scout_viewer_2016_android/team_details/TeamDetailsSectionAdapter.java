package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.RankingsActivity;
import org.citruscircuits.scout_viewer_2016_android.RankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class TeamDetailsSectionAdapter extends RankingsSectionAdapter {
    private String[][] fieldsToDisplay = {
            {"calculatedData.firstPickAbility"},
            {"calculatedData.avgHighShotsAuto", "calculatedData.avgLowShotsAuto"},
            {"calculatedData.avgHighShotsTele", "calculatedData.avgLowShotsTele"},
            {"calculatedData.avgSuccessfulTimesCrossedDefensesAuto.a.cdf",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.a.pc",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.b.mt",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.b.rp",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.c.db",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.c.sp",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.d.rt",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.d.rw",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.e.lb",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.a.cdf",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.a.pc",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.b.mt",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.b.rp",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.c.db",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.c.sp",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.d.rt",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesTele.d.rw",
                    "calculatedData.avgSuccessfulTimesCrossedDefensesAuto.e.lb"},
            {"calculatedData.siegePower"}};

    private String[] sectionTitles = {"High Level", "Auto", "Teleop", "Defenses", "Siege"};
    private Integer teamNumber;
    private Context context;

    public TeamDetailsSectionAdapter(Context context, Integer teamNumber) {
        super(context);
        this.teamNumber = teamNumber;
        this.context = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyDataSetChanged();
            }
        }, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));
    }


    @Override
    public Object getRowItem(int section, int row) {
            return fieldsToDisplay[section][row];
    }

    @Override
    public int numberOfSections() {
        return sectionTitles.length;
    }

    @Override
    public int numberOfRows(int section) {
        if (section > -1) {
            return fieldsToDisplay[section].length;
        } else {
            return 0;
        }
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }

    @Override
    public Object getSectionHeaderItem(int section) {
        return sectionTitles[section];
    }

    @Override
    public int getRankOfRowInSection(int section, int row) {
        String fieldName = (String)getRowItem(section, row);
        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());
        List<Object> teams = new ArrayList<>();
        teams.addAll(FirebaseLists.teamsList.getValues());
        int rank = Utils.getRankOfObject(team, teams, fieldName);
        return rank;
    }

    @Override
    public String getNameOfRowInSection(int section, int row) {
        return Constants.KEYS_TO_TITLES.get(getRowItem(section, row));
    }

    @Override
    public Object getValueOfRowInSection(int section, int row) {
        if (FirebaseLists.teamsList.getKeys().contains(teamNumber.toString())) {
            return Utils.getObjectField(FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString()), (String)getRowItem(section, row));
        } else {
            return -1;
        }
    }

    @Override
    public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id) {
        Intent rankingsActivityIntent = new Intent(context, RankingsActivity.class);
        rankingsActivityIntent.putExtra("field", (String) getRowItem(section, row));

        context.startActivity(rankingsActivityIntent);
    }
}