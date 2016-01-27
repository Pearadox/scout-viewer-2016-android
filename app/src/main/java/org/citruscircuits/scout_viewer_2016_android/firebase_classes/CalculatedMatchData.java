package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

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
}