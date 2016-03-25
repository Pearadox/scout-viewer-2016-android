package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class TeamInMatchData extends Object {
	public Integer teamNumber;
	public Integer matchNumber;
	public CalculatedTeamInMatchData calculatedData;

	public String scoutName;

	public Boolean didGetIncapacitated;
	public Boolean didGetDisabled;

	public Integer numTimesUnaffected;
	public Integer numTimesSlowed;
	public Integer numTimesBeached;

	public Integer rankTorque;
	public Integer rankSpeed;
	public Integer rankDefense;
	public Integer rankBallControl;
	public Integer rankAgility;

	//Auto
	public List<Integer> ballsIntakedAuto;
	public Integer numBallsKnockedOffMidlineAuto;
	public Map<String, List<Long>> timesSuccessfulCrossedDefensesAuto;
	public Map<String, List<Long>> timesFailedCrossedDefensesAuto;
	public Integer numHighShotsMadeAuto;
	public Integer numLowShotsMadeAuto;
	public Integer numHighShotsMissedAuto;
	public Integer numLowShotsMissedAuto;
	public Boolean didReachAuto;

	//Tele
	public Integer numHighShotsMadeTele;
	public Integer numLowShotsMadeTele;
	public Integer numHighShotsMissedTele;
	public Integer numLowShotsMissedTele;
	public Integer numGroundIntakesTele;
	public Integer numShotsBlockedTele;
	public Boolean didScaleTele;
	public Boolean didChallengeTele;
	public Map<String, List<Long>> timesSuccessfulCrossedDefensesTele;
	public Map<String, List<Long>> timesFailedCrossedDefensesTele;

	public String superNotes;
}