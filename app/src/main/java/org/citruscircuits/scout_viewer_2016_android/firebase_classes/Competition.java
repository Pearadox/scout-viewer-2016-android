package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Competition extends Object {
	public String code;
	public List<Team> teams;
	public List<Match> matches;
	public List<TeamInMatchData> TIMDs;
	public Integer averageScore;
}
	