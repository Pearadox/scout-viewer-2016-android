package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 1/31/16.
 */
public abstract class MatchRankingsAdapter extends RankingsAdapter<Match> {
    public MatchRankingsAdapter(Context context, String fieldName, boolean isNotReversed) {
        super(context, fieldName, fieldName, isNotReversed);
    }

    @Override
    public boolean filter(Match value, String scope) {
        List<Integer> teamsInMatch = new ArrayList<>();
        teamsInMatch.addAll(value.redAllianceTeamNumbers);
        teamsInMatch.addAll(value.blueAllianceTeamNumbers);

        for (Integer team : teamsInMatch) {
            String teamNumberString = team.toString();
            if (teamNumberString.contains(searchString)) {
                return true;
            }
        }

        String matchNumberString = value.number.toString();
        if (matchNumberString.contains(searchString)) {
            return true;
        }

        return false;
    }

    @Override
    public List<Match> getFirebaseList() {
        return FirebaseLists.matchesList.getValues();
    }

    @Override
    public String getBroadcastAction() {
        return Constants.MATCHES_UPDATED_ACTION;
    }

    @Override
    public String getRankCellText(Match value) {
        return "Q" + value.number.toString();
    }

    @Override
    public boolean isImportant(String valueTitle) {
        return StarManager.isImportantMatch(Integer.parseInt(valueTitle));
    }

    @Override
    public void makeImportant(String valueTitle) {
        StarManager.removeImportantMatch(Integer.parseInt(valueTitle));
    }

    @Override
    public void makeNoLongerImportant(String valueTitle) {
        StarManager.removeStarredTeam(Integer.parseInt(valueTitle));
    }

    @Override
    public void respondToClick(String valueTitle) {
        // Do nothing right now...
    }
}
