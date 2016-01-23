package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Competition implements Parcelable {
	public String code;
	public Team[] teams;
	public Match[] matches;
	public TeamInMatchData[] TIMDs;
	public int averageScore;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.code);
		dest.writeArray(this.teams);
		dest.writeArray(this.matches);
		dest.writeArray(this.TIMDs);
		dest.writeInt(this.averageScore);
	}

	public Competition() {
	}

	private Competition(Parcel in) {
		this.code = in.readString();
        this.teams = (Team[])in.readArray(Team[].class.getClassLoader());
        this.matches = (Match[])in.readArray(Match[].class.getClassLoader());
        this.TIMDs = (TeamInMatchData[])in.readArray(TeamInMatchData[].class.getClassLoader());
		this.averageScore = in.readInt();
	}

	public static final Parcelable.Creator<Competition> CREATOR = new Parcelable.Creator<Competition>() {
		public Competition createFromParcel(Parcel source) {
			return new Competition(source);
		}

		public Competition[] newArray(int size) {
			return new Competition[size];
		}
	};
}
	