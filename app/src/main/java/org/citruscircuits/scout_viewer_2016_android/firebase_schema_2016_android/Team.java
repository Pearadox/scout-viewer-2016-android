package org.citruscircuits.scout_viewer_2016_android.firebase_schema_2016_android;

import org.citruscircuits.scout_viewer_2016_android.firebase_schema_2016_android.CalculatedTeamData;
import org.citruscircuits.scout_viewer_2016_android.firebase_schema_2016_android.Match;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Team {
	public String name;
	public int number;
	public Match[] matches;
	public TeamInMatchData teamInMatchDatas;
	public CalculatedTeamData calculatedTeamData;
}
