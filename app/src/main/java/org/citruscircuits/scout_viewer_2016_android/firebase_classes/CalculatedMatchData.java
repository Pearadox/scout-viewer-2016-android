package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedMatchData extends Object {
    public Float predictedRedScore;
    public Float predictedBlueScore;
    public Integer numDefensesCrossedByBlue;
    public Integer numDefensesCrossedByRed;
    public Float predictedBlueRPs;
    public Float predictedRedRPs;
    public Integer actualBlueRPs;
    public Integer actualRedRPs;
    public Boolean redAllianceDidBreach;
    public Boolean blueAllianceDidBreach;
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
    public Float sdPredictedRedScore;
    public Float sdPredictedBlueScore;
    public List<String> optimalRedDefenses;
    public List<String> optimalBlueDefenses;
}