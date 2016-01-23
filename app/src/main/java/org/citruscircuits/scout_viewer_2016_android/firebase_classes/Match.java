package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Match implements Parcelable {
	public int number;
	public CalculatedMatchData calculatedData;
	public int[] redAllianceTeamNumbers;
	public int[] blueAllianceTeamNumbers;
	public int blueScore;
	public int redScore;
	public int[] redDefensePositions;
	public int[] blueDefensePositions;
	public boolean redAllianceDidCapture;
	public boolean blueAllianceDidCapture;


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.number);
		dest.writeParcelable(this.calculatedData, 0);
		dest.writeIntArray(this.redAllianceTeamNumbers);
		dest.writeIntArray(this.blueAllianceTeamNumbers);
		dest.writeInt(this.blueScore);
		dest.writeInt(this.redScore);
		dest.writeIntArray(this.redDefensePositions);
		dest.writeIntArray(this.blueDefensePositions);
		dest.writeByte(redAllianceDidCapture ? (byte) 1 : (byte) 0);
		dest.writeByte(blueAllianceDidCapture ? (byte) 1 : (byte) 0);
	}

	public Match() {
	}

	private Match(Parcel in) {
		this.number = in.readInt();
		this.calculatedData = in.readParcelable(CalculatedMatchData.class.getClassLoader());
		this.redAllianceTeamNumbers = in.createIntArray();
		this.blueAllianceTeamNumbers = in.createIntArray();
		this.blueScore = in.readInt();
		this.redScore = in.readInt();
		this.redDefensePositions = in.createIntArray();
		this.blueDefensePositions = in.createIntArray();
		this.redAllianceDidCapture = in.readByte() != 0;
		this.blueAllianceDidCapture = in.readByte() != 0;
	}

	public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
		public Match createFromParcel(Parcel source) {
			return new Match(source);
		}

		public Match[] newArray(int size) {
			return new Match[size];
		}
	};
}