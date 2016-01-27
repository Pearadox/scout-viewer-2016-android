package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedTeamData {
	public float firstPickAbility;
	public Map<Integer, Float> secondPickAbility;
	public float driverAbility;
	public float highShotAccuracyAuto;
	public float lowShotAccuracyAuto;
	public float highShotAccuracyTele;
	public float lowShotAccuracyTele;
	public float avgGroundIntakes;
	public float avgHighShotsTele;
	public float avgLowShotsTele;
	public float avgShotsBlocked;
	public float avgHighShotsAuto;
	public float avgLowShotsAuto;
	public float avgMidlineBallsIntakedAuto;
	public float avgBallsKnockedOffMidlineAuto;
	public float avgTorque;
	public float avgSpeed;
	public float avgEvasion;
	public float avgDefense;
	public float avgBallControl;
	public float disfunctionalPercentage;
	public float reachPercentage;
	public float disabledPercentage;
	public float incapacitatedPercentage;
	public float scalePercentage;
	public float challengePercentage;
	public Map<String, Map<String, Float>> avgTimesCrossedDefensesAuto;
	public Map<String, Map<String, Float>> avgTimesCrossedDefensesTele;
	public float siegePower;
	public float siegeConsistency;
	public float siegeAbility;
	public float numRPs;
	public float numAutoPoints;
	public float numScaleAndChallengePoints;
	public float sdHighShotsAuto;
	public float sdHighShotsTele;
	public float sdLowShotsAuto;
	public float sdLowShotsTele;
	public float sdGroundIntakes;
	public float sdShotsBlocked;
	public float sdMidlineBallsIntakedAuto;
	public float sdBallsKnockedOffMidlineAuto;
	public Map<String, Float> sdDefenseCrossesAuto;
	public Map<String, Float> sdDefenseCrossesTele;
}