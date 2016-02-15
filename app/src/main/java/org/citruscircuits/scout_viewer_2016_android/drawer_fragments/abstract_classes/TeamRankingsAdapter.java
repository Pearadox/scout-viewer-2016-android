package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.content.Intent;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsActivity;

import java.util.List;

/**
 * Created by colinunger on 1/31/16.
 */
public abstract class TeamRankingsAdapter extends RankingsAdapter<Team> {
    public TeamRankingsAdapter(Context context, String rankFieldName, String valueFieldName, boolean isNotReversed) {
        super(context, rankFieldName, valueFieldName, isNotReversed);
    }

    @Override
    public boolean filter(Team value, String scope) {
        String teamNumberString = value.number.toString();
        return teamNumberString.indexOf(searchString) == 0;
    }

    @Override
    public List<Team> getFirebaseList() {
        return FirebaseLists.teamsList.getValues();
    }

    @Override
    public String getBroadcastAction() {
        return Constants.TEAMS_UPDATED_ACTION;
    }

    @Override
    public String getRankCellText(Team value) {
        return value.number.toString();
    }

    @Override
    public boolean isImportant(String valueTitle) {
        return StarManager.isStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void makeImportant(String valueTitle) {
        StarManager.addStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void makeNoLongerImportant(String valueTitle) {
        StarManager.removeStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void respondToClick(String valueTitle) {
        Integer teamNumberClicked = Integer.parseInt(valueTitle);
        Intent teamDetailsViewIntent = new Intent(context, TeamDetailsActivity.class);
        teamDetailsViewIntent.putExtra("teamNumber", teamNumberClicked);
        teamDetailsViewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(teamDetailsViewIntent);
    }
}
