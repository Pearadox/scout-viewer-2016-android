package org.citruscircuits.scout_viewer_2016_android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class Utils {
    public static Object getObjectField(Object object, String fieldName) {
        try {
            String[] fieldNames = fieldName.split("\\.");
            Object modObject = object;
            for (String singleFieldLevelName : fieldNames) {
                if (modObject instanceof Map) {
                    LinkedHashMap lhm = (LinkedHashMap)modObject;
                    modObject = lhm.get(singleFieldLevelName);
                } else {
                    Field f = modObject.getClass().getDeclaredField(singleFieldLevelName);
                    f.setAccessible(true);

                    modObject = f.get(modObject);
                }
            }

            return modObject;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean fieldIsNotNull(Object object, String fieldName) {
        return getObjectField(object, fieldName) != null;
    }

    public static Integer getRankOfObject(Object o, List<Object> os, String fieldName) {
        if (Utils.getObjectField(o, fieldName) == null) {
            return null;
        }
        Collections.sort(os, new ObjectFieldComparator(fieldName, true));
        return os.indexOf(o);
    }

    public static String dataPointToPercentage(Float dataPoint, int decimalPlaces) {
        if (dataPoint != null) {
            return roundDataPoint(dataPoint * 100, decimalPlaces, "??") + "%";
        } else {
            return "???";
        }
    }

    public static String getMatchDisplayValue(Match match, String key) {
        return roundDataPoint(getObjectField(match, key), 2, "???");
    }

    public static String getDisplayValue(Object object, String key) {
        return roundDataPoint(getObjectField(object, key), 2, "???");
    }

    public static String roundDataPoint(Object dataPoint, int decimalPlaces, String unkownValue) {
        if (dataPoint == null) {
            return unkownValue;
        }
        int decimalPointIndex = dataPoint.toString().indexOf(".");
        if (decimalPointIndex < 0) {
            return  dataPoint.toString();
        }
        int substringIndex = dataPoint.toString().indexOf(".") + 1 + decimalPlaces;
        if (decimalPlaces < 1) {
            substringIndex--;
        }
        return dataPoint.toString().substring(0, Math.min(substringIndex, dataPoint.toString().length()));
    }

    public static Integer getLastMatchPlayed() {
        Integer lastMatch = 0;
        for (Match match : FirebaseLists.matchesList.getValues()) {
            if(match.redScore != null || match.blueScore != null) {
                lastMatch = match.number;
            }
        }

        return lastMatch;
    }

    public static SpannableString highlightTextInString(String fullString, String toHighlight) {
        SpannableString spannableString = new SpannableString(fullString);
        int index = fullString.indexOf(toHighlight);
        if (index == 0) {
            spannableString.setSpan(new BackgroundColorSpan(Color.GREEN), fullString.indexOf(toHighlight), fullString.indexOf(toHighlight) + toHighlight.length(), 0);
        }
        return spannableString;
    }

    public static List<TeamInMatchData> getTeamInMatchDatasForTeamNumber(Integer teamNumber) {
        List<TeamInMatchData> teamInMatchDatas = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : FirebaseLists.teamInMatchDataList.getValues()) {
            Log.e("test", "Team in match data: " + teamInMatchData.teamNumber + teamInMatchData.matchNumber);
            if (teamInMatchData.teamNumber.equals(teamNumber)) {
                teamInMatchDatas.add(teamInMatchData);
            }
        }

        Collections.sort(teamInMatchDatas, new ObjectFieldComparator("matchNumber", true));
        return teamInMatchDatas;
    }

    public static List<Integer> getMatchNumbersForTeamNumber(Integer teamNumber) {
        List<Integer> matchNumbers = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : getTeamInMatchDatasForTeamNumber(teamNumber)) {
            matchNumbers.add(teamInMatchData.matchNumber);
        }

        return matchNumbers;
    }
}
