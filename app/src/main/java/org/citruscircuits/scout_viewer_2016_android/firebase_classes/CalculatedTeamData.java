package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedTeamData extends Object{
	public Float firstPickAbility;
	public Map<String, Float> secondPickAbility;
	public Float driverAbility;
	public Float highShotAccuracyAuto;
	public Float lowShotAccuracyAuto;
	public Float highShotAccuracyTele;
	public Float lowShotAccuracyTele;
	public Float avgGroundIntakes;
	public Float avgHighShotsTele;
	public Float avgLowShotsTele;
	public Float avgShotsBlocked;
	public Float avgHighShotsAuto;
	public Float avgLowShotsAuto;
	public Float avgMidlineBallsIntakedAuto;
	public Float avgBallsKnockedOffMidlineAuto;
	public Float avgTorque;
	public Float avgSpeed;
	public Float avgEvasion;
	public Float avgDefense;
	public Float avgBallControl;
	public Float disfunctionalPercentage;
	public Float reachPercentage;
	public Float disabledPercentage;
	public Float incapacitatedPercentage;
	public Float scalePercentage;
	public Float challengePercentage;
	public Float autoAbility;
	public Map<String, Float> avgSuccessfulTimesCrossedDefenses;
	public Map<String, Float> avgSuccessfulTimesCrossedDefensesAuto;
	public Map<String, Float> avgSuccessfulTimesCrossedDefensesTele;
	public Map<String, Float> avgFailedTimesCrossedDefensesAuto;
	public Map<String, Float> avgFailedTimesCrossedDefensesTele;
	public Map<String, Float> avgTimeForDefenseCrossAuto;
	public Map<String, Float> avgTimeForDefenseCrossTele;
	public Float siegePower;
	public Float siegeConsistency;
	public Float siegeAbility;
	public Float numRPs;
	public Float numAutoPoints;
	public Float numScaleAndChallengePoints;
	public Float sdHighShotsAuto;
	public Float sdHighShotsTele;
	public Float sdLowShotsAuto;
	public Float sdLowShotsTele;
	public Float sdGroundIntakes;
	public Float sdShotsBlocked;
	public Float sdMidlineBallsIntakedAuto;
	public Float sdBallsKnockedOffMidlineAuto;
	public Map<String, Map<String, Float>> sdSuccessfulDefenseCrossesAuto;
	public Map<String, Map<String, Float>> sdSuccessfulDefenseCrossesTele;
	public Map<String, Map<String, Float>> sdFailedDefenseCrossesAuto;
	public Map<String, Map<String, Float>> sdFailedDefenseCrossesTele;
	public Integer actualSeed;
    public Integer predictedSeed;
	public Float predictedNumRPs;
	public Float scoreContribution;
	public Float citrusDPR;
	public Float RScoreTorque;
	public Float RScoreSpeed;
	public Float RScoreEvasion;
	public Float RScoreDefense;
	public Float RScoreBallControl;
	public Float RScoreDrivingAbility;
	public Float overallSecondPickAbility;
	public Float breachPercentage;
	public Float blockingAbility;
	public Float teleopShotAbility;
}