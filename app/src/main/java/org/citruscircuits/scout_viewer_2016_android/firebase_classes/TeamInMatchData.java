package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class TeamInMatchData implements Parcelable {
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
	public Integer[] ballsIntakedAuto;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.teamNumber);
		dest.writeInt(this.matchNumber);
		dest.writeByte(didGetIncapacitated ? (byte) 1 : (byte) 0);
		dest.writeByte(didGetDisabled ? (byte) 1 : (byte) 0);
		dest.writeMap(this.rankDefenseCrossingEffectiveness);
		dest.writeInt(this.rankTorque);
		dest.writeInt(this.rankSpeed);
		dest.writeInt(this.rankEvasion);
		dest.writeInt(this.rankDefense);
		dest.writeInt(this.rankBallControl);
        dest.writeArray(ballsIntakedAuto);
		dest.writeInt(this.numBallsKnockedOffMidlineAuto);
        dest.writeMap(this.timesDefensesCrossedAuto);
		dest.writeInt(this.numHighShotsMadeAuto);
		dest.writeInt(this.numLowShotsMadeAuto);
		dest.writeInt(this.numHighShotsMissedAuto);
		dest.writeInt(this.numLowShotsMissedAuto);
		dest.writeByte(didReachAuto ? (byte) 1 : (byte) 0);
		dest.writeInt(this.numHighShotsMadeTele);
		dest.writeInt(this.numLowShotsMadeTele);
		dest.writeInt(this.numHighShotsMissedTele);
		dest.writeInt(this.numLowShotsMissedTele);
		dest.writeInt(this.numGroundIntakesTele);
		dest.writeInt(this.numShotsBlockedTele);
		dest.writeByte(didScaleTele ? (byte) 1 : (byte) 0);
		dest.writeByte(didChallengeTele ? (byte) 1 : (byte) 0);
        dest.writeMap(this.timesDefensesCrossedTele);
	}

	public TeamInMatchData() {
	}

	private TeamInMatchData(Parcel in) {
		this.teamNumber = in.readInt();
		this.matchNumber = in.readInt();
		this.didGetIncapacitated = in.readByte() != 0;
		this.didGetDisabled = in.readByte() != 0;
        in.readMap(this.rankDefenseCrossingEffectiveness, Map.class.getClassLoader());
		this.rankTorque = in.readInt();
		this.rankSpeed = in.readInt();
		this.rankEvasion = in.readInt();
		this.rankDefense = in.readInt();
		this.rankBallControl = in.readInt();
		this.ballsIntakedAuto = (Integer[])in.readArray(Integer[].class.getClassLoader());
		this.numBallsKnockedOffMidlineAuto = in.readInt();
        in.readMap(this.rankDefenseCrossingEffectiveness, Map.class.getClassLoader());
        in.readMap(this.timesDefensesCrossedAuto, Map.class.getClassLoader());
		this.numHighShotsMadeAuto = in.readInt();
		this.numLowShotsMadeAuto = in.readInt();
		this.numHighShotsMissedAuto = in.readInt();
		this.numLowShotsMissedAuto = in.readInt();
		this.didReachAuto = in.readByte() != 0;
		this.numHighShotsMadeTele = in.readInt();
		this.numLowShotsMadeTele = in.readInt();
		this.numHighShotsMissedTele = in.readInt();
		this.numLowShotsMissedTele = in.readInt();
		this.numGroundIntakesTele = in.readInt();
		this.numShotsBlockedTele = in.readInt();
		this.didScaleTele = in.readByte() != 0;
		this.didChallengeTele = in.readByte() != 0;
        in.readMap(this.timesDefensesCrossedTele, Map.class.getClassLoader());
	}

	public static final Parcelable.Creator<TeamInMatchData> CREATOR = new Parcelable.Creator<TeamInMatchData>() {
		public TeamInMatchData createFromParcel(Parcel source) {
			return new TeamInMatchData(source);
		}

		public TeamInMatchData[] newArray(int size) {
			return new TeamInMatchData[size];
		}
	};
}