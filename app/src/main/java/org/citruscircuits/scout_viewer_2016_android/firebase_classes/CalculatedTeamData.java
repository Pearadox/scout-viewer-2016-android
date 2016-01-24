package org.citruscircuits.scout_viewer_2016_android.firebase_classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by citruscircuits on 1/17/16
 */

public class CalculatedTeamData implements Parcelable {
	public int driverAbility;
	public float highShotAccuracyAuto;
	public float lowShotAccuracyAuto;
	public float highShotAccuracyTele;
	public float lowShotAccuracyTele;
	public float avgGroundIntakes;
	public float avgHighShotsTele;
	public float avgLowShotsTele;
	public float avgShotsBlocked;
	public float avgHighShotsAuto;
	public float avgLowShotsAuto;
	public float avgMidlineBallsIntakedAuto;
	public float avgBallsKnockedOffMidlineAuto;
	public float avgTorque;
	public float avgSpeed;
	public float avgEvasion;
	public float avgDefense;
	public float avgBallControl;
	public float disfunctionalPercentage;
	public float reachPercentage;
	public float disabledPercentage;
	public float incapacitatedPercentage;
	public float scalePercentage;
	public float challengePercentage;
	public Map<String, Float> avgDefenseCrossingEffectiveness;
	public Map<String, Float> avgTimesCrossedDefensesAuto;
	public Map<String, Float> avgTimesCrossedDefensesTele;
	public Float siegePower;
	public float siegeConsistency;
	public float siegeAbility;
	public int numRPs;
	public int numAutoPoints;
	public int numScaleAndChallangePoints;


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.driverAbility);
		dest.writeFloat(this.highShotAccuracyAuto);
		dest.writeFloat(this.lowShotAccuracyAuto);
		dest.writeFloat(this.highShotAccuracyTele);
		dest.writeFloat(this.lowShotAccuracyTele);
		dest.writeFloat(this.avgGroundIntakes);
		dest.writeFloat(this.avgHighShotsTele);
		dest.writeFloat(this.avgLowShotsTele);
		dest.writeFloat(this.avgShotsBlocked);
		dest.writeFloat(this.avgHighShotsAuto);
		dest.writeFloat(this.avgLowShotsAuto);
		dest.writeFloat(this.avgMidlineBallsIntakedAuto);
		dest.writeFloat(this.avgBallsKnockedOffMidlineAuto);
		dest.writeFloat(this.avgTorque);
		dest.writeFloat(this.avgSpeed);
		dest.writeFloat(this.avgEvasion);
		dest.writeFloat(this.avgDefense);
		dest.writeFloat(this.avgBallControl);
		dest.writeFloat(this.disfunctionalPercentage);
		dest.writeFloat(this.reachPercentage);
		dest.writeFloat(this.disabledPercentage);
		dest.writeFloat(this.incapacitatedPercentage);
		dest.writeFloat(this.scalePercentage);
		dest.writeFloat(this.challengePercentage);
		dest.writeMap(this.avgDefenseCrossingEffectiveness);
		dest.writeMap(this.avgTimesCrossedDefensesAuto);
		dest.writeMap(this.avgTimesCrossedDefensesTele);
		dest.writeFloat(this.siegePower);
		dest.writeFloat(this.siegeConsistency);
		dest.writeFloat(this.siegeAbility);
		dest.writeInt(this.numRPs);
		dest.writeInt(this.numAutoPoints);
		dest.writeInt(this.numScaleAndChallangePoints);
	}

	public CalculatedTeamData() {
	}

	private CalculatedTeamData(Parcel in) {
		this.driverAbility = in.readInt();
		this.highShotAccuracyAuto = in.readFloat();
		this.lowShotAccuracyAuto = in.readFloat();
		this.highShotAccuracyTele = in.readFloat();
		this.lowShotAccuracyTele = in.readFloat();
		this.avgGroundIntakes = in.readFloat();
		this.avgHighShotsTele = in.readFloat();
		this.avgLowShotsTele = in.readFloat();
		this.avgShotsBlocked = in.readFloat();
		this.avgHighShotsAuto = in.readFloat();
		this.avgLowShotsAuto = in.readFloat();
		this.avgMidlineBallsIntakedAuto = in.readFloat();
		this.avgBallsKnockedOffMidlineAuto = in.readFloat();
		this.avgTorque = in.readFloat();
		this.avgSpeed = in.readFloat();
		this.avgEvasion = in.readFloat();
		this.avgDefense = in.readFloat();
		this.avgBallControl = in.readFloat();
		this.disfunctionalPercentage = in.readFloat();
		this.reachPercentage = in.readFloat();
		this.disabledPercentage = in.readFloat();
		this.incapacitatedPercentage = in.readFloat();
		this.scalePercentage = in.readFloat();
		this.challengePercentage = in.readFloat();
		in.readMap(this.avgDefenseCrossingEffectiveness, Map.class.getClassLoader());
		in.readMap(this.avgTimesCrossedDefensesAuto, Map.class.getClassLoader());
		in.readMap(this.avgTimesCrossedDefensesTele, Map.class.getClassLoader());
		this.siegePower = in.readFloat();
		this.siegeConsistency = in.readFloat();
		this.siegeAbility = in.readFloat();
		this.numRPs = in.readInt();
		this.numAutoPoints = in.readInt();
		this.numScaleAndChallangePoints = in.readInt();
	}

	public static final Parcelable.Creator<CalculatedTeamData> CREATOR = new Parcelable.Creator<CalculatedTeamData>() {
		public CalculatedTeamData createFromParcel(Parcel source) {
			return new CalculatedTeamData(source);
		}

		public CalculatedTeamData[] newArray(int size) {
			return new CalculatedTeamData[size];
		}
	};
}