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
	public Float avgAgility;
	public Float avgDefense;
	public Float avgBallControl;
	public Float disfunctionalPercentage;
	public Float reachPercentage;
	public Float disabledPercentage;
	public Float incapacitatedPercentage;
	public Float scalePercentage;
	public Float challengePercentage;
	public Float autoAbility;
	public Float sdAutoAbility;
	public Map<String, Float> predictedSuccessfulCrossingsForDefenseTele;
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
	public Float sdTeleopShotAbility;
	public Float sdSiegeAbility;
	public Integer actualNumRPs;
	public Map<String, Float> sdSuccessfulDefenseCrossesAuto;
	public Map<String, Float> sdSuccessfulDefenseCrossesTele;
	public Map<String, Float> sdFailedDefenseCrossesAuto;
	public Map<String, Float> sdFailedDefenseCrossesTele;
	public Integer actualSeed;
    public Integer predictedSeed;
	public Float predictedNumRPs;
	public Float scoreContribution;
	public Float citrusDPR;
	public Float RScoreTorque;
	public Float RScoreSpeed;
	public Float RScoreAgility;
	public Float RScoreDefense;
	public Float RScoreBallControl;
	public Float RScoreDrivingAbility;
	public Float overallSecondPickAbility;
	public Float breachPercentage;
	public Float blockingAbility;
	public Float teleopShotAbility;
	public Float twoBallAutoAccuracy;
	public Float avgHighShotsAttemptedTele;
	public Map<String, Float> crossingsSuccessRateForDefenseAuto;
	public Float twoBallAutoTriedPercentage;
	public Map<String, Float> crossingsSuccessRateForDefenseTele;
	public Float avgDrivingAbility;
	public Float avgLowShotsAttemptedTele;
	public Map<String, Float> avgNumTimesSlowed;
	public Map<String, Float> avgNumTimesBeached;
	public Map<String, Float> avgNumTimesUnaffected;
	public Map<String, Float> slowedPercentage;
	public Map<String, Float> unaffectedPercentage;
	public Map<String, Float> beachedPercentage;
}