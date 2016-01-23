package org.citruscircuits.scout_viewer_2016_android.firebase_classes;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by citruscircuits on 1/17/16
 */

public class Team implements Parcelable {
	public String name;
	public int number;
	public Match[] matches;
	public TeamInMatchData teamInMatchDatas;
	public CalculatedTeamData calculatedData;


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.number);
        dest.writeArray(this.matches);
		dest.writeParcelable(this.teamInMatchDatas, 0);
		dest.writeParcelable(this.calculatedData, 0);
	}

	public Team() {
	}

	private Team(Parcel in) {
		this.name = in.readString();
		this.number = in.readInt();
        this.matches = (Match[])in.readArray(Match[].class.getClassLoader());
		this.teamInMatchDatas = in.readParcelable(TeamInMatchData.class.getClassLoader());
		this.calculatedData = in.readParcelable(CalculatedTeamData.class.getClassLoader());
	}

	public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
		public Team createFromParcel(Parcel source) {
			return new Team(source);
		}

		public Team[] newArray(int size) {
			return new Team[size];
		}
	};
}
