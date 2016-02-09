package org.citruscircuits.scout_viewer_2016_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamRankingsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by colinunger on 2/8/16.
 */
public abstract class MultitypeRankingsSectionAdapter extends RankingsSectionAdapter {
//    private Integer teamNumber;
    public Context context;

    public MultitypeRankingsSectionAdapter(Context context) {
        super(context);
        this.context = context;
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyDataSetChanged();
            }
        }, new IntentFilter(getUpdatedAction()));
    }


    @Override
    public Object getRowItem(int section, int row) {
        return getFieldsToDisplay()[section][row];
    }

    @Override
    public int numberOfSections() {
        return getSectionTitles().length;
    }

    @Override
    public int numberOfRows(int section) {
        if (section > -1) {
            return getFieldsToDisplay()[section].length;
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
        return getSectionTitles()[section];
    }

    @Override
    public int getRankOfRowInSection(int section, int row) {
        String fieldName = (String)getRowItem(section, row);
        Object object = getObject();
        int rank = Utils.getRankOfObject(object, getObjectList(), fieldName);
        return rank;
    }

    @Override
    public String getNameOfRowInSection(int section, int row) {
        return Constants.KEYS_TO_TITLES.get(getRowItem(section, row));
    }

    @Override
    public String getValueOfRowInSection(int section, int row) {
        String fieldKey = (String)getRowItem(section, row);
//        if (FirebaseLists.teamsList.getKeys().contains(teamNumber.toString())) {
        if (Utils.getObjectField(getObject(), fieldKey).getClass().equals(String.class)) {
            return (String)Utils.getObjectField(getObject(), fieldKey);
        } else if (Utils.getObjectField(getObject(), fieldKey).getClass().equals(Boolean.class)) {
            return ((Boolean)Utils.getObjectField(getObject(), fieldKey)) ? "Yes" : "No";
        } else if (Utils.getObjectField(getObject(), fieldKey).getClass().equals(Integer.class)) {
            return Utils.getObjectField(getObject(), fieldKey).toString();
        } else if (Arrays.asList(getPercentageFields()).contains(fieldKey)) {
            Float value = (Float)Utils.getObjectField(getObject(), fieldKey);
            return Utils.dataPointToPercentage(value, 2);
        } else {
            Float value = (Float)Utils.getObjectField(getObject(), fieldKey);
            return Utils.roundDataPoint(value, 2);
        }

//            return Utils.getObjectField(FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString()), (String)getRowItem(section, row)).toString();
//        } else {
//            return "";
//        }
    }

    @Override
    public boolean isUnranked(int section, int row) {
        return (Arrays.asList(getUnrankedFields()).contains(getRowItem(section, row)));
    }

    @Override
    public boolean isOtherTypeOfView(int section, int row) {
        return (Arrays.asList(getLongTextFields()).contains(getRowItem(section, row)));
    }

    @Override
    public View getOtherTypeOfView(int section, int row) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View longTextCellView = inflater.inflate(R.layout.long_text_cell, null, false);
        TextView titleTextView = (TextView)longTextCellView.findViewById(R.id.teamNumberTextView);
        titleTextView.setText(getNameOfRowInSection(section, row));
        TextView longTextView = (TextView)longTextCellView.findViewById(R.id.longTextView);
        longTextView.setText(getValueOfRowInSection(section, row));
        return longTextCellView;
    }

    @Override
    public void onRowItemClick(AdapterView<?> parent, View view, int section, int row, long id) {
        if(!isUnranked(section, row) && !isOtherTypeOfView(section, row)) {
            Intent rankingsActivityIntent = new Intent(context, TeamRankingsActivity.class);
            rankingsActivityIntent.putExtra("field", (String) getRowItem(section, row));

            context.startActivity(rankingsActivityIntent);
        }
    }

    @Override
    public boolean isRowEnabled(int section, int row) {
        return !isUnranked(section, row) && !isOtherTypeOfView(section, row);
    }

    public abstract String[][] getFieldsToDisplay();
    public abstract String[] getSectionTitles();
    public abstract String[] getUnrankedFields();
    public abstract String[] getLongTextFields();
    public abstract String[] getPercentageFields();
    public abstract String getUpdatedAction();
    public abstract Object getObject();
    public abstract List<Object> getObjectList();
}
