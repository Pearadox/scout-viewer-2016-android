package org.citruscircuits.scout_viewer_2016_android;

import org.citruscircuits.scout_viewer_2016_android.FirebaseList;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;

/**
 * Created by colinunger on 1/30/16.
 */
public class FirebaseLists {
    public static FirebaseList<Team> teamsList;
    public static FirebaseList<Match> matchesList;
    public static FirebaseList<TeamInMatchData> teamInMatchDataList;
}
