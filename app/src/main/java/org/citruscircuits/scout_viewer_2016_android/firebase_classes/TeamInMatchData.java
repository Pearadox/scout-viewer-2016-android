package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class TeamInMatchData {
	public int teamNumber;
	public int matchNumber;
	
	public boolean didGetIncapacitated;
	public boolean didGetDisabled;

	public Map<String, Integer> rankDefenseCrossingEffectiveness;
	public int rankTorque;
	public int rankSpeed;
	public int rankEvasion;
	public int rankDefense;
	public int rankBallControl;

	//Auto
	public int[] ballsIntakedAuto;
	public int numBallsKnockedOffMidlineAuto;
	public Map<String, Integer> timesDefensesCrossedAuto;
	public int numHighShotsMadeAuto;
	public int numLowShotsMadeAuto;
	public int numHighShotsMissedAuto;
	public int numLowShotsMissedAuto;
	public boolean didReachAuto;

	//Tele
	public int numHighShotsMadeTele;
	public int numLowShotsMadeTele;
	public int numHighShotsMissedTele;
	public int numLowShotsMissedTele;
	public int numGroundIntakesTele;
	public int numShotsBlockedTele;
	public boolean didScaleTele;
	public boolean didChallengeTele;
	public Map<String, Integer> timesDefensesCrossedTele;
}
