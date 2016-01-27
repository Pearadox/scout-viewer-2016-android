package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Match {
	public int number;
	public CalculatedMatchData calculatedData;
	public int[] redAllianceTeamNumbers;
	public int[] blueAllianceTeamNumbers;
	public int redScore;
	public int blueScore;
	public String[] redDefensePositions;
	public String[] blueDefensePositions;
	public boolean redAllianceDidCapture;
	public boolean blueAllianceDidCapture;
}