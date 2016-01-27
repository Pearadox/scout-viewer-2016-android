package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Competition {
	public String code;
	public Team[] teams;
	public Match[] matches;
	public TeamInMatchData[] TIMDs;
	public int averageScore;
}
	