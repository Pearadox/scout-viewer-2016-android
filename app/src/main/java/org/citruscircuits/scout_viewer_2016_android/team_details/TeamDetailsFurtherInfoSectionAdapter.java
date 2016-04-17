package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MultitypeRankingsSectionAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamDetailsFurtherInfoSectionAdapter extends MultitypeRankingsSectionAdapter {
    Integer teamNumber;
    public TeamDetailsFurtherInfoSectionAdapter(Context context, Integer teamNumber) {
        super(context);
        this.teamNumber = teamNumber;
    }

    @Override
    public String[] getUnrankedFields() {
        return new String[]{};
    }

    @Override
    public String[] getLongTextFields() {
        return new String[]{};
    }


    @Override
    public String[] getFurtherInformationFields() {
        return new String[]{};
    }

    @Override
    public String[] getNotClickableFields() {
        return new String[]{};
    }


    @Override
    public void handleNonDefaultClick(int section, int row) {
        Intent intent = new Intent(context, TeamRankingsActivity.class);
        intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row));
        context.startActivity(intent);
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
    public boolean onRowItemLongClick (AdapterView<?> parent, View view, int section, int row, long id) {
        if (!isUnranked(section, row)) {
            Intent intent = new Intent(context, TeamRankingsActivity.class);
            intent.putExtra("teamNumber", teamNumber).putExtra("field", (String)getRowItem(section,row));
            context.startActivity(intent);
        }
        return true;
    }
}
