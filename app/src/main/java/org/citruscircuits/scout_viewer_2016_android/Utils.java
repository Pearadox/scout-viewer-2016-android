package org.citruscircuits.scout_viewer_2016_android;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.CalculatedMatchData;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.CalculatedTeamData;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.CalculatedTeamInMatchData;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        return getRankOfObject(o, os, fieldName, true);
    }

    public static Integer getRankOfObject(Object o, List<Object> os, String fieldName, boolean isReversed) {
        if (Utils.getObjectField(o, fieldName) == null) {
            return null;
        }
        try {
            Collections.sort(os, new ObjectFieldComparator(fieldName, isReversed));
            return os.indexOf(o);
        } catch (IllegalArgumentException iae) {
            return null;
        }
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
            if((match.redScore != null) || (match.blueScore != null)) {
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



    public static JSONObject serializeClass(Object object) throws IllegalAccessException, JSONException {
        JSONObject data = new JSONObject();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if ((field.getType().isAssignableFrom(CalculatedMatchData.class)) || (field.getType().isAssignableFrom(CalculatedTeamData.class))
                        || ((field.getType().isAssignableFrom(CalculatedTeamInMatchData.class)))) {
                    data.put(field.getName(), serializeClass(field.get(object)));
                } else if (field.getType().isAssignableFrom(List.class)) {
                    JSONArray array = new JSONArray();
                    List list = (List) field.get(object);
                    for (int i = 0; i < list.size(); i++) {
                        array.put(list.get(i));
                    }
                    data.put(field.getName(), array);
                } else {
                    data.put(field.getName(), field.get(object));
                }
            } catch (NullPointerException npe) {
                continue;
            }
        }
        return data;
    }
    //not sketchy at all
    public static Object deserializeJsonObject(Object object, JSONObject data) throws IllegalAccessException {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if ((field.getType().isAssignableFrom(CalculatedMatchData.class)) || (field.getType().isAssignableFrom(CalculatedTeamData.class))
                        || ((field.getType().isAssignableFrom(CalculatedTeamInMatchData.class)))) {
                    field.set(object, deserializeJsonObject(field.get(object), data.getJSONObject(field.getName())));
                } else if (field.getType().isAssignableFrom(List.class)) {
                    List<Object> array = new ArrayList<>();
                    JSONArray dataArray = data.getJSONArray(field.getName());
                    for (int i = 0; i < dataArray.length(); i++) {
                        array.add(dataArray.get(i));
                    }
                    field.set(object, array);
                } else {
                    field.set(object, data.get(field.getName()));
                }
            } catch (JSONException|NullPointerException e) {
                continue;
            }
        }
        return object;
    }
    //do I use reflection too much? probably
    //anyway this method is used to display data points that aren't on firebase.  Basically it calls a getter method for the field on the utils class
    public static Object getViewerObjectField(Object object, String fieldName, Intent args) {
        try {
            Method method = ViewerDataPoints.class.getMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1), object.getClass(), Intent.class);
            return method.invoke(new ViewerDataPoints(), object, args);
        } catch (Exception e) {
            Log.e("Method error", "Requested viewer object that doesn't exist!");
            return null;
        }
    }
    public static String getViewerObjectFieldRank(String fieldName, Intent args) {
        try {
            Method method = ViewerDataPoints.class.getMethod("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "RankingsValue", Intent.class);
            return (String)method.invoke(new ViewerDataPoints(), args);
        } catch (Exception e) {
            Log.e("Method error", "Requested viewer object that doesn't exist!");
            return null;
        }
    }
}
