package org.citruscircuits.scout_viewer_2016_android.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.RemoteViews;

import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MainActivity;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by colinunger on 2/6/16.
 */
public class StarManager extends Service {

    private NotificationManager mNM;
    private Map<String, ValueEventListener> valueEventListeners = new HashMap<>();
    private static Integer currentMatchNumber;
    private static Integer nextImportantMatch;
    public static List<Integer> importantMatches = new ArrayList<>();
    public static List<Integer> starredTeams = new ArrayList<>();
    public static Map<Integer, List<Integer>> matchesAddedByTeam = new HashMap<>();

    public class StarBinder extends Binder {
        StarManager getService() {
            return StarManager.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("test", "Starting matches listener service");

        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        currentMatchNumber = Utils.getLastMatchPlayed();

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currentMatchNumber = Utils.getLastMatchPlayed();
                nextImportantMatch = getNextImportantMatch();
                notifyOfNewMatchIfNeeded();
            }
        }, new IntentFilter(Constants.MATCHES_UPDATED_ACTION));

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                currentMatchNumber = Utils.getLastMatchPlayed();
                nextImportantMatch = getNextImportantMatch();
                notifyOfNewMatchIfNeeded();
            }
        }, new IntentFilter(Constants.STARS_MODIFIED_ACTION));

        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new StarBinder();



    public void notifyOfNewMatchIfNeeded() {
        if (FirebaseLists.matchesList.getKeys().contains(nextImportantMatch.toString())) {
            if (nextImportantMatch - currentMatchNumber <= 3) {
                Match match = FirebaseLists.matchesList.getFirebaseObjectByKey(nextImportantMatch.toString());

                notifyOfNewMatchPlayed(match, nextImportantMatch - currentMatchNumber - 1);
            }
        }
    }

    public Integer getNextImportantMatch() {
        Integer nextImportantMatch = 0;

        for (Integer matchNumber : getImportantMatches()) {
            if (matchNumber > currentMatchNumber) {
                nextImportantMatch = matchNumber;
                break;
            }
        }

        return nextImportantMatch;
    }

    public static void addImportantMatch(Integer matchNumber) {
        if (!importantMatches.contains(matchNumber)) {
            importantMatches.add(matchNumber);
            Collections.sort(importantMatches);
            saveToSharedPreferences();
        }
    }

    public static void removeImportantMatch(Integer matchNumber) {
        if (importantMatches.contains(matchNumber)) {
            importantMatches.remove(matchNumber);
            Collections.sort(importantMatches);
            for (List<Integer> matchesAdded : matchesAddedByTeam.values()) {
                matchesAdded.remove(matchNumber);
            }
            saveToSharedPreferences();
        }
    }

    public static void addStarredTeam(Integer teamNumber) {
        if (!starredTeams.contains(teamNumber)) {
            starredTeams.add(teamNumber);
            matchesAddedByTeam.put(teamNumber, getNonImportantMatchesForTeam(teamNumber));
            for (Integer matchNumber : Utils.getMatchNumbersForTeamNumber(teamNumber)) {
                addImportantMatch(matchNumber);
            }
            saveToSharedPreferences();
        }
    }

    public static void removeStarredTeam(Integer teamNumber) {
        if (starredTeams.contains(teamNumber)) {
            starredTeams.remove(teamNumber);
            Collections.sort(starredTeams);
            List<Integer> matchesAdded = new ArrayList<>(matchesAddedByTeam.get(teamNumber));
            for (Integer matchNumber : matchesAdded) {
                removeImportantMatch(matchNumber);
            }
            saveToSharedPreferences();
        }
    }

    public static boolean isStarredTeam(Integer teamNumber) {
        return starredTeams.contains(teamNumber);
    }

    public static boolean isImportantMatch(Integer matchNumber) {
        return getImportantMatches().contains(matchNumber);
    }

    public static List<Integer> getImportantMatches() {
        return new ArrayList<>(importantMatches);
    }

    private static List<Integer> getNonImportantMatchesForTeam(Integer teamNumber) {
        List<Integer> nonStarredMatches = new ArrayList<>();
        for (Integer matchNumber : Utils.getMatchNumbersForTeamNumber(teamNumber)) {
            if (!importantMatches.contains(matchNumber)) {
                nonStarredMatches.add(matchNumber);
            }
        }

        return nonStarredMatches;
    }

    public static void saveToSharedPreferences() {
        LocalBroadcastManager.getInstance(ViewerApplication.appContext).sendBroadcast(new Intent(Constants.STARS_MODIFIED_ACTION));
        JSONArray starredTeamsJSON = new JSONArray(starredTeams);
        JSONArray importantMatchesJSON = new JSONArray(importantMatches);
        Map<String, List<Integer>> tempMap = new HashMap<>();
        for (Integer key : matchesAddedByTeam.keySet()) {
            tempMap.put(key.toString(), matchesAddedByTeam.get(key));
        }
        JSONObject matchesAddedByTeamJSON = new JSONObject(tempMap);
        ViewerApplication.sharedPreferences.edit().putString("starredTeams", starredTeamsJSON.toString()).commit();
        ViewerApplication.sharedPreferences.edit().putString("importantMatches", importantMatchesJSON.toString()).commit();
        ViewerApplication.sharedPreferences.edit().putString("matchesAddedByTeam", matchesAddedByTeamJSON.toString()).commit();
    }

    public static Integer getCurrentMatchNumber() {
        return currentMatchNumber;
    }

    public void notifyOfNewMatchPlayed(Match match, Integer matchesFromNow) {
        RemoteViews notificationRemoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.match_notification);

        if (matchesFromNow.equals(0)) {
            notificationRemoteViews.setTextViewText(R.id.matchNotificationTitleTextView, "Don't miss Q" + match.number + ", starting now!");
        } else if (matchesFromNow.equals(1)) {
            notificationRemoteViews.setTextViewText(R.id.matchNotificationTitleTextView, "Don't miss Q" + match.number + ", starting in " + matchesFromNow.toString() + " match!");
        } else {
            notificationRemoteViews.setTextViewText(R.id.matchNotificationTitleTextView, "Don't miss Q" + match.number + ", starting in " + matchesFromNow.toString() + " matches!");
        }
        notificationRemoteViews.setTextViewText(R.id.matchNumber, match.number.toString());

        List<Integer> teams = new ArrayList<>();
        teams.addAll(match.redAllianceTeamNumbers);
        teams.addAll(match.blueAllianceTeamNumbers);

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            notificationRemoteViews.setTextViewText(teamTextViewIDs[i], teams.get(i).toString());
            if (teams.get(i).equals(Constants.TEAM_NUMBER)) {
                notificationRemoteViews.setTextColor(teamTextViewIDs[i], Color.GREEN);
            }
        }

        if (match.redScore != null || match.blueScore != null) {
            notificationRemoteViews.setTextViewText(R.id.redScore, (match.redScore != null) ? match.redScore + "" : "???");
            notificationRemoteViews.setTextViewText(R.id.blueScore, (match.blueScore != null) ? match.blueScore + "" : "???");
            notificationRemoteViews.setTextColor(R.id.redScore, Color.argb(255, 255, 0, 0));
            notificationRemoteViews.setTextColor(R.id.blueScore, Color.argb(255, 0, 0, 255));
        } else {
            notificationRemoteViews.setTextViewText(R.id.redScore, (Utils.fieldIsNotNull(match, "calculatedData.predictedRedScore")) ? match.redScore + "" : "???");
            notificationRemoteViews.setTextViewText(R.id.blueScore, (Utils.fieldIsNotNull(match, "calculatedData.predictedBlueScore")) ? match.blueScore + "" : "???");
            notificationRemoteViews.setTextColor(R.id.redScore, Color.argb(75, 255, 0, 0));
            notificationRemoteViews.setTextColor(R.id.blueScore, Color.argb(75, 0, 0, 255));
        }


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContent(notificationRemoteViews);
        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

        int mId = 1678;
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentTitle("Don't miss match " + match.number + ", happening " + matchesFromNow.toString() + " matches from now!");
        mBuilder.setVibrate(new long[] {100, 100, 100, 100, 100});
        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }


}