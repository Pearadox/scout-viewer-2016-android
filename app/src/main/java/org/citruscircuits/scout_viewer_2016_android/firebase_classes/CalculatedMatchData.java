package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedMatchData implements Parcelable {
    public float predictedRedScore;
    public float predictedBlueScore;
    public int numDefenseCrossesByBlue;
    public int numDefenseCrossesByRed;
    public int blueRPs;
    public int redRPs;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.predictedRedScore);
        dest.writeFloat(this.predictedBlueScore);
        dest.writeInt(this.numDefenseCrossesByBlue);
        dest.writeInt(this.numDefenseCrossesByRed);
        dest.writeInt(this.blueRPs);
        dest.writeInt(this.redRPs);
    }

    public CalculatedMatchData() {
    }

    private CalculatedMatchData(Parcel in) {
        this.predictedRedScore = in.readFloat();
        this.predictedBlueScore = in.readFloat();
        this.numDefenseCrossesByBlue = in.readInt();
        this.numDefenseCrossesByRed = in.readInt();
        this.blueRPs = in.readInt();
        this.redRPs = in.readInt();
    }

    public static final Parcelable.Creator<CalculatedMatchData> CREATOR = new Parcelable.Creator<CalculatedMatchData>() {
        public CalculatedMatchData createFromParcel(Parcel source) {
            return new CalculatedMatchData(source);
        }

        public CalculatedMatchData[] newArray(int size) {
            return new CalculatedMatchData[size];
        }
    };
}