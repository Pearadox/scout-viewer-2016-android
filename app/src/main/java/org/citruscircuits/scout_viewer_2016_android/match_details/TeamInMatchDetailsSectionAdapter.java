package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.RankingsActivity;
import org.citruscircuits.scout_viewer_2016_android.RankingsSectionAdapter;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 1/31/16.
 */
public class TeamInMatchDetailsSectionAdapter extends RankingsSectionAdapter {
    private String[][] fieldsToDisplay = {{"numHighShotsMadeTele"}};

    private String[] sectionTitles = {"Teleop"};
    private Integer teamNumber;
    private Integer matchNumber;
    private Context context;

    public TeamInMatchDetailsSectionAdapter(Context context, Integer teamNumber, Integer matchNumber) {
        super(context);
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
        this.context = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyDataSetChanged();
            }
        }, new IntentFilter(Constants.TEAM_IN_MATCH_DATAS_UPDATED_ACTION));
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
        return fieldsToDisplay[section].length;
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
        String fieldName = (String) getRowItem(section, row);
//        TeamInMatchData teamInMatchData = FirebaseLists.teamInMatchDataList.getFirebaseObjectByKey(teamNumber.toString() + "Q" + matchNumber.toString());
//        List<Object> TeamInMatch = new ArrayList<>();
//        teams.addAll(FirebaseLists.teamsList.getValues());
//        int rank = Utils.getRankOfObject(team, teams, fieldName);
        return 0;
    }

    @Override
    public String getNameOfRowInSection(int section, int row) {
        return Constants.KEYS_TO_TITLES.get(getRowItem(section, row));
    }

    @Override
    public Object getValueOfRowInSection(int section, int row) {
        if (FirebaseLists.teamsList.getKeys().contains(teamNumber.toString())) {
            String key = teamNumber.toString() + "Q" + matchNumber.toString();
            return Utils.getObjectField(FirebaseLists.teamInMatchDataList.getFirebaseObjectByKey(key), (String)getRowItem(section, row));
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
