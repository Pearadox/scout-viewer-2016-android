package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Team extends Object {
	public String name;
	public Integer number;
	public List<Match> matches;
	public List<TeamInMatchData> teamInMatchDatas;
	public CalculatedTeamData calculatedData;
	public String selectedImageUrl;
	public Map<String, String> otherImageUrls;
	public Boolean pitLowBarCapability;
	public Integer pitPotentialLowBarCapability;
	public Integer pitPotentialCDFAndPCCapability;
	public Integer pitPotentialMidlineBallCapability;
	public Integer pitOrganization;
	public Float pitFrontBumperWidth;
	public Integer pitPotentialShotBlockerCapability;
	public String pitNotes;
	public Integer pitNumberOfWheels;
	public Integer pitHeightOfRobot;
	public Float pitBumperHeight;
	public Float pitDriveBaseWidth;
	public Float pitDriveBaseLength;
}
