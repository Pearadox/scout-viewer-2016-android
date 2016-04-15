package org.citruscircuits.scout_viewer_2016_android;

import android.content.Intent;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

public class ViewerDataPoints {
    //getter method for viewer data point
    public static Integer getMatchesUntilNextMatchForTeam(Team team, Intent args) {
        Integer currentMatch = Utils.getLastMatchPlayed();
        for (Integer matchNumber : Utils.getMatchNumbersForTeamNumber(team.number)) {
            if (matchNumber > currentMatch) {
                return matchNumber - currentMatch;
            }
        }
        return null;
    }
    public static String getDefenseCrossingTeamDetailsTitle(Team team, Intent args) {
        String ending = args.getStringExtra("defense");
        return "A: " + Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.avgSuccessfulTimesCrossedDefensesAuto." + ending), 1, "???")
                + " T: " + Utils.roundDataPoint(Utils.getObjectField(team, "calculatedData.avgSuccessfulTimesCrossedDefensesTele." + ending), 1, "???");
    }
    public static String getDefenseCrossingTeamDetailsTitleRankingsValue(Intent args) {
        return "calculatedData.avgSuccessfulTimesCrossedDefenses." + args.getStringExtra("defense");
    }
}
