package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedMatchData {
    public float predictedRedScore;
    public float predictedBlueScore;
    public int numDefensesCrossedByBlue;
    public int numDefensesCrossedByRed;
    public float predictedBlueRPs;
    public float predictedRedRPs;
    public int actualBlueRPs;
    public int actualRedRPs;
    public boolean redAllianceDidBreach;
    public boolean blueAllianceDidBreach;
    public Map<String, Float> redScoresForDefenses;
    public Map<String, Float> redWinningChanceForDefenses;
    public Map<String, Float> redBreachChanceForDefenses;
    public Map<String, Float> redRPsForDefenses;
    public Map<String, Float> blueScoresForDefenses;
    public Map<String, Float> blueWinningChanceForDefenses;
    public Map<String, Float> blueBreachChanceForDefenses;
    public Map<String, Float> blueRPsForDefenses;
    public Float redWinChance;
    public Float blueWinChance;
    public Float redBreachChance;
    public Float blueBreachChance;
    public Float redCaptureChance;
    public Float blueCaptureChance;
}