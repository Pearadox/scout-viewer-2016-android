package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class Constants {
    public static final String ORIGINAL_ROOT_FIREBASE_PATH = "https://1678-scouting-2016.firebaseio.com/";
    public static String ROOT_FIREBASE_PATH = ORIGINAL_ROOT_FIREBASE_PATH;
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Our Schedule", "Starred Matches", "Schedule", "Seeding", "Predicted Seeding", "First Pick", "Overall Second Pick", "Super Data"};
    public static String MATCHES_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Matches";
    public static String TEAMS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "Teams";
    public static String TEAM_IN_MATCH_DATAS_PATH = ORIGINAL_ROOT_FIREBASE_PATH + "TeamInMatchDatas";
    public static final String TEAMS_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.teamsupdated";
    public static final String MATCHES_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.matchesupdated";
    public static final String TEAM_IN_MATCH_DATAS_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.teaminmatchdatasupdated";
    public static final String NEW_TEAM_PHOTO_ACTION = "org.citruscircuits.scout_viewer_2016_android.newteamphoto";
    public static final String NEW_MATCH_PLAYED_ACTION = "org.citruscircuits.scout_viewer_2016_android.newmatchplayed";
    public static final String STARS_MODIFIED_ACTION = "org.citruscircuits.scout_viewer_2016_android.starsmodified";
    public static final String[] MATCH_SCOPES = {"Team", "Match"};
    public static final String[] TEAM_SCOPES = {"Team"};
    public static final int STAR_COLOR = Color.argb(255, 255, 255, 204);
    public static final int TEAM_NUMBER = 1678;
    public static final Map<String, String> KEYS_TO_TITLES;
    public static final Map<String, String> DATA_TO_GRAPH;
    public static final Map<String, String> FIREBASE_KEYS = new HashMap<String, String>() {{
        put("https://1678-dev2-2016.firebaseio.com/", "hL8fStivTbHUXM8A0KXBYPg2cMsl80EcD7vgwJ1u");
        put("https://1678-dev-2016.firebaseio.com/","j1r2wo3RUPMeUZosxwvVSFEFVcrXuuMAGjk6uPOc");
        put("https://1678-dev3-2016.firebaseio.com/", "AEduO6VFlZKD4v10eW81u9j3ZNopr5h2R32SPpeq");
        put("https://1678-scouting-2016.firebaseio.com/", "qVIARBnAD93iykeZSGG8mWOwGegminXUUGF2q0ee");
        put("https://1678-strat-dev-2016.firebaseio.com/", "IMXOxXD3FjOOUoMGJlkAK5pAtn89mGIWAEnaKJhP");
    }};
    public static final List<String> DEFENSE_ENDINGS = new ArrayList<>(Arrays.asList("pc", "cdf", "mt", "rp", "db", "sp", "rw", "rt", "lb"));


    //static means run on class load.  We declare our lists here so we can edit them before 'making them final'
    static {
        Map<String, String> initialKeysToTitlesMap = new HashMap<String, String> () {
            {
                put("calculatedData.avgHighShotsAuto", "Avg. Auto High Shots");
                put("calculatedData.avgLowShotsAuto", "Avg. Auto Low Shots");
                put("calculatedData.avgHighShotsTele", "Avg. Tele High Shots");
                put("calculatedData.avgLowShotsTele", "Avg. Tele Low Shots");
                put("pitFrontBumperWidth", "Bumper Width");
                put("pitLowBarCapability", "Can Low Bar?");
                put("pitPotentialLowBarCapability", "Could Low Bar?");
                put("pitPotentialMidlineBallCapability", "Could Midline?");
                put("pitDriveBaseWidth", "Base Width");
                put("pitDriveBaseLength", "Base Length");
                put("pitBumperHeight", "Bumper Height");
                put("pitPotentialShotBlockerCapability", "Could Block?");
                put("pitNotes", "Notes");
                put("pitOrganization", "Pit Organization");
                put("pitNumberOfWheels", "Num. Wheels");
                put("calculatedData.firstPickAbility", "First Pick Ability");
                put("calculatedData.secondPickAbility", "Second Pick Ability");
                put("calculatedData.driverAbility", "Driver Ability");
                put("calculatedData.highShotAccuracyAuto", "Auto High Shot Acc.");
                put("calculatedData.lowShotAccuracyAuto", "Auto Low Shot Acc.");
                put("calculatedData.highShotAccuracyTele", "Tele High Shot Acc.");
                put("calculatedData.lowShotAccuracyTele", "Tele Low Shot Acc.");
                put("calculatedData.avgGroundIntakes", "Avg. Ground Intakes");
                put("calculatedData.avgHighShotsTele", "Avg. Tele High");
                put("calculatedData.avgLowShotsTele", "Avg. Tele Low");
                put("calculatedData.avgShotsBlocked", "Avg. Shots Blocked");
                put("calculatedData.avgHighShotsAuto", "Avg. Auto High");
                put("calculatedData.avgLowShotsAuto", "Avg. Auto Low");
                put("calculatedData.avgMidlineBallsIntakedAuto", "Avg. Mid. Balls");
                put("calculatedData.avgBallsKnockedOffMidlineAuto", "Avg. Mid. Knocked");
                put("calculatedData.avgTorque", "Avg. Torque");
                put("calculatedData.avgSpeed", "Avg. Speed");
                put("calculatedData.avgAgility", "Avg. Agility");
                put("calculatedData.avgDefense", "Avg. Defense");
                put("calculatedData.avgBallControl", "Avg. Ball Control");
                put("calculatedData.RScoreTorque", "Torque");
                put("calculatedData.RScoreSpeed", "Speed");
                put("calculatedData.RScoreAgility", "Agility");
                put("calculatedData.RScoreDefense", "Defense");
                put("calculatedData.RScoreBallControl", "Ball Control");
                put("calculatedData.RScoreDrivingAbility", "Driving Ability");
                put("calculatedData.disfunctionalPercentage", "Disfunctional");
                put("calculatedData.reachPercentage", "Reach");
                put("calculatedData.disabledPercentage", "Disabled");
                put("calculatedData.incapacitatedPercentage", "Incapacitated");
                put("calculatedData.scalePercentage", "Scale");
                put("calculatedData.challengePercentage", "Challenge");
                put("calculatedData.siegePower", "Siege Power");
                put("calculatedData.siegeConsistency", "Siege Consistency");
                put("calculatedData.siegeAbility", "Siege Ability");
                put("calculatedData.predictedNumRPs", "Predicted RPs");
                put("calculatedData.actualNumRPs", "Num. RPs");
                put("calculatedData.numAutoPoints", "Avg Auto Pts.");
                put("calculatedData.numScaleAndChallengePoints", "Num. S&C Pts.");
                put("calculatedData.predictedSeed", "Predicted Seed");
                put("calculatedData.actualSeed", "Seed");
                put("calculatedData.avgSuccessfulTimesCrossedDefensesAuto.DEFENSE", "Avg. Auto DEFENSE Cross");
                put("calculatedData.avgFailedTimesCrossedDefensesAuto.DEFENSE", "Avg. Auto DEFENSE Fails");
                put("calculatedData.avgSuccessfulTimesCrossedDefensesTele.DEFENSE", "Avg. Tele DEFENSE Cross");
                put("calculatedData.avgFailedTimesCrossedDefensesTele.DEFENSE", "Avg. Tele DEFENSE Fails");
                put("calculatedData.twoBallAutoAccuracy", "Two Ball Auto Accuracy");
                put("calculatedData.twoBallAutoTriedPercentage", "Two Ball Auto Attempted");
                put("didGetIncapacitated", "Incapacitated?");
                put("didGetDisabled", "Disabled?");
                put("rankTorque", "Torque");
                put("rankSpeed", "Speed");
                put("rankAgility", "Agility");
                put("rankDefense", "Defense");
                put("rankBallControl", "Ball Control");
                put("ballsIntakedAuto", "Mid. Balls Int.");
                put("numHighShotsMadeAuto", "Auto High Shots");
                put("numLowShotsMadeAuto", "Auto Low Shots");
                put("numHighShotsMissedAuto", "Auto High Misses");
                put("numLowShotsMissedAuto", "Auto Low Misses");
                put("didReachAuto", "Reach?");
                put("numHighShotsMadeTele", "Tele High Shots");
                put("numLowShotsMadeTele", "Tele Low Shots");
                put("numHighShotsMissedTele", "Tele High Misses");
                put("numLowShotsMissedTele", "Tele Low Misses");
                put("numGroundIntakesTele", "Tele Ground Intakes");
                put("numShotsBlockedTele", "Shots Blocked");
                put("didScaleTele", "Scale?");
                put("didChallengeTele", "Challenge?");
                put("timesSuccessfulCrossedDefensesTele.DEFENSE", "Tele DEFENSE Cross");
                put("timesFailedCrossedDefensesTele.DEFENSE", "Tele DEFENSE Fail");
                put("timesSuccessfulCrossedDefensesAuto.DEFENSE", "Auto DEFENSE Cross");
                put("timesFailedCrossedDefensesAuto.DEFENSE", "Auto DEFENSE Fail");
                put("superNotes", "Notes");
                put("numBallsKnockedOffMidlineAuto", "Knocked Off Mid.");
                put("numBallsKnockedOffMidlineAuto", "Knocked Off Mid.");
                put("calculatedData.citrusDPR", "Citrus DPR");
                put("calculatedData.overallSecondPickAbility", "Overall Second Pick Ability");
                put("cdf", "Cheval de Frise");
                put("pc", "Portcullis");
                put("mt", "Moat");
                put("rp", "Ramparts");
                put("db", "Drawbridge");
                put("sp", "Sally Port");
                put("rt", "Rough Terrain");
                put("rw", "Rock Wall");
                put("lb", "Low Bar");
                put("calculatedData.avgSuccessfulTimesCrossedDefenses.DEFENSE", "Avg. DEFENSE Cross");
                put("calculatedData.avgTimeForDefenseCrossAuto.DEFENSE", "Avg. Time DEFENSE Auto");
                put("calculatedData.avgTimeForDefenseCrossTele.DEFENSE", "Avg. Time DEFENSE Tele");
                put("calculatedData.predictedSuccessfulCrossingsForDefenseTele.DEFENSE", "Predicted Cross DEFENSE Tele");
                put("calculatedData.crossingsSuccessRateForDefenseAuto.DEFENSE", "Success Rate DEFENSE Auto");
                put("calculatedData.crossingsSuccessRateForDefenseTele.DEFENSE", "Success Rate DEFENSE Tele");
                put("calculatedData.sdSuccessfulDefenseCrossesAuto.DEFENSE", "SD. Success DEFENSE Auto");
                put("calculatedData.sdSuccessfulDefenseCrossesTele.DEFENSE", "SD. Success DEFENSE Tele");
                put("calculatedData.sdFailedDefenseCrossesAuto.DEFENSE", "SD. Fail DEFENSE Auto");
                put("calculatedData.sdFailedDefenseCrossesTele.DEFENSE", "SD. Fail DEFENSE Tele");
                put("teamNumber", "Team Number");
                put("matchNumber", "Match Number");
                put("matches", "Matches");
                put("calculatedData.sdHighShotsAuto", "SD. High Shot Auto");
                put("calculatedData.sdHighShotsTele", "SD. High Shot Tele");
                put("calculatedData.sdLowShotsAuto", "SD. Low Shot Auto");
                put("calculatedData.sdLowShotsTele", "SD. Low Shot Tele");
                put("VIEWER.matchesUntilNextMatchForTeam", "Matches Until Next Match");
                put("VIEWER.defenseCrossingTeamDetailsTitle.DEFENSE", "DEFENSE");
                put("calculatedData.slowedPercentage.DEFENSE", "DEFENSE Slowed Percentage");
                put("calculatedData.beachedPercentage.DEFENSE", "DEFENSE Beached Percentage");
                put("pitCheesecakeAbility", "Cheesecake Ease");
                put("pitAvailableWeight", "Avail. Weight");
                put("calculatedData.autoAbility", "Auto Ability");
                put("autoDetails", "More Auto");
                put("teleDetails", "More Teleop");
                put("superDetails", "Super Data");
                put("pitDetails", "Pit Data");
                put("pitProgrammingLanguage", "Program Language");
                put("calculatedData.avgNumTimesCrossedDefensesAuto", "Avg. Crosses Auto");
                put("calculatedData.autoAbilityExcludeD", "Auto Points W/O Def. D");
                put("calculatedData.autoAbilityExcludeLB", "Auto Points W/O LB");
                put("calculatedData.defensesCrossableAuto", "Defenses Crossable Auto");
                put("calculatedData.defensesCrossableTele", "Defenses Crossable Tele");
            }
        };
        Map<String, String> initialDatasToGraphMap = new HashMap<String, String> () {
            {
                put("calculatedData.avgNumTimesCrossedDefensesAuto", "calculatedData.totalNumTimesCrossedDefensesAuto");
                put("calculatedData.actualNumRPs", "calculatedData.numRPs");
                put("calculatedData.avgHighShotsAuto", "numHighShotsMadeAuto");
                put("calculatedData.avgLowShotsAuto", "numLowShotsMadeAuto");
                put("calculatedData.avgHighShotsTele", "numHighShotsMadeTele");
                put("calculatedData.avgLowShotsTele", "numLowShotsMadeTele");
                put("calculatedData.firstPickAbility", "calculatedData.firstPickAbility");
                put("calculatedData.highShotAccuracyAuto", "calculatedData.highShotAccuracyAuto");
                put("calculatedData.lowShotAccuracyAuto", "calculatedData.lowShotAccuracyAuto");
                put("calculatedData.highShotAccuracyTele", "calculatedData.highShotAccuracyTele");
                put("calculatedData.lowShotAccuracyTele", "calculatedData.lowShotAccuracyTele");
                put("calculatedData.avgGroundIntakes", "numGroundIntakesTele");
                put("calculatedData.avgHighShotsTele", "numHighShotsMadeTele");
                put("calculatedData.avgLowShotsTele", "numLowShotsMadeTele");
                put("calculatedData.avgShotsBlocked", "numShotsBlockedTele");
                put("calculatedData.avgHighShotsAuto", "numHighShotsMadeAuto");
                put("calculatedData.avgLowShotsAuto", "numLowShotsAuto");
                put("calculatedData.avgMidlineBallsIntakedAuto", "calculatedData.numBallsIntakedOffMidlineAuto");
                put("calculatedData.avgBallsKnockedOffMidlineAuto", "numBallsKnockedOffMidlineAuto");
                put("calculatedData.avgTorque", "rankTorque");
                put("calculatedData.avgSpeed", "rankSpeed");
                put("calculatedData.avgAgility", "rankAgility");
                put("calculatedData.avgDefense", "rankDefense");
                put("calculatedData.avgBallControl", "rankBallControl");
                put("calculatedData.RScoreTorque", "rankTorque");
                put("calculatedData.RScoreSpeed", "rankSpeed");
                put("calculatedData.RScoreAgility", "rankAgility");
                put("calculatedData.RScoreDefense", "rankDefense");
                put("calculatedData.RScoreBallControl", "rankBallControl");
                put("calculatedData.RScoreDrivingAbility", "calculatedData.drivingAbility");
                put("calculatedData.reachPercentage", "didReachAuto");
                put("calculatedData.disabledPercentage", "didGetDisabled");
                put("calculatedData.incapacitatedPercentage", "didGetIncapacitated");
                put("calculatedData.scalePercentage", "didScaleTele");
                put("calculatedData.challengePercentage", "didChallengeTele");
                put("calculatedData.siegePower", "calculatedData.siegePower");
                put("calculatedData.siegeConsistency", "calculatedData.siegeConsistency");
                put("calculatedData.siegeAbility", "calculatedData.siegeAbility");
                put("calculatedData.numAutoPoints", "calculatedData.numAutoPoints");
                put("calculatedData.numScaleAndChallengePoints", "calculatedData.numScaleAndChallengePoints");
                put("calculatedData.avgSuccessfulTimesCrossedDefensesAuto.DEFENSE", "calculatedData.numTimesSuccesfulCrossedDefensesAuto.DEFENSE");
                put("calculatedData.avgFailedTimesCrossedDefensesAuto.DEFENSE", "calculatedData.numTimesFailedCrossedDefensesAuto.DEFENSE");
                put("calculatedData.avgSuccessfulTimesCrossedDefensesTele.DEFENSE", "calculatedData.numTimesSuccesfulCrossedDefensesTele.DEFENSE");
                put("calculatedData.avgFailedTimesCrossedDefensesTele.DEFENSE", "calculatedData.numTimesFailedCrossedDefensesTele.DEFENSE");
                put("calculatedData.overallSecondPickAbility", "calculatedData.overallSecondPickAbility");
                put("calculatedData.avgTimeForDefenseCrossAuto.DEFENSE", "calculatedData.crossingTimeForDefenseAuto.DEFENSE");
                put("calculatedData.avgTimeForDefenseCrossTele.DEFENSE", "calculatedData.crossingTimeForDefenseTele.DEFENSE");
                put("calculatedData.crossingsSuccessRateForDefenseAuto.DEFENSE", "calculatedData.crossingsForDefensePercentageAuto.DEFENSE");
                put("calculatedData.crossingsSuccessRateForDefenseTele.DEFENSE", "calculatedData.crossingsForDefensePercentageTele.DEFENSE");
                put("calculatedData.slowedPercentage.DEFENSE", "numTimesSlowed");
                put("calculatedData.beachedPercentage.DEFENSE", "numTimesBeached");
                put("calculatedData.autoAbility", "calculatedData.autoAbility");
            }
        };
        //replace all 'DEFENSE's with the correct defenses
        Map<String, String> modifiableKeysToTitlesMap = new HashMap<>(initialKeysToTitlesMap);
        for (Map.Entry<String, String> entry : initialKeysToTitlesMap.entrySet()) {
            if (entry.getKey().endsWith(".DEFENSE")) {
                modifiableKeysToTitlesMap.remove(entry.getKey());
                for (String defense : DEFENSE_ENDINGS) {
                    modifiableKeysToTitlesMap.put(entry.getKey().replaceAll("DEFENSE", defense), entry.getValue().replaceAll("DEFENSE", defense.toUpperCase()));
                }
            }
        }
        KEYS_TO_TITLES = modifiableKeysToTitlesMap;
        Map<String, String> modifiableDatasToGraphMap = new HashMap<>(initialDatasToGraphMap);
        for (Map.Entry<String, String> entry : initialDatasToGraphMap.entrySet()) {
            if (entry.getKey().endsWith(".DEFENSE")) {
                modifiableDatasToGraphMap.remove(entry.getKey());
                for (String defense : DEFENSE_ENDINGS) {
                    modifiableDatasToGraphMap.put(entry.getKey().replaceAll("DEFENSE", defense), entry.getValue().replaceAll("DEFENSE", defense));
                }
            }
        }
        DATA_TO_GRAPH = modifiableDatasToGraphMap;
    }
}
