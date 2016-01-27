package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Team {
	public String name;
	public int number;
	public Match[] matches;
	public TeamInMatchData[] teamInMatchDatas;
	public CalculatedTeamData calculatedData;
	public String selectedImageUrl;
	public String[] otherImageUrls;
	public boolean pitLowBarCapability;
	public boolean pitPotentialLowBarCapability;
	public boolean pitPotentialCDFAndPCCapability;
	public boolean pitPotentialMidlineBallCapability;
	public int pitOrganization;
	public float pitFrontBumperWidth;
	public boolean pitPotentialShotBlockerCapability;
	public String pitNotes;
	public int pitNumberOfWheels;
	public int pitHeightOfRobot;
}
