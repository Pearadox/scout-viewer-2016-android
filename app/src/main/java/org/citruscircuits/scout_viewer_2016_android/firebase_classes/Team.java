package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

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
	public List<String> otherImageUrls;
	public Boolean pitLowBarCapability;
	public Boolean pitPotentialLowBarCapability;
	public Boolean pitPotentialCDFAndPCCapability;
	public Boolean pitPotentialMidlineBallCapability;
	public Integer pitOrganization;
	public Float pitFrontBumperWidth;
	public Boolean pitPotentialShotBlockerCapability;
	public String pitNotes;
	public Integer pitNumberOfWheels;
	public Integer pitHeightOfRobot;
}
