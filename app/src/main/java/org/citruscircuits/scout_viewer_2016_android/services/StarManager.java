package org.citruscircuits.scout_viewer_2016_android.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.MainActivity;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

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
    private Integer currentMatchNumber;
    private Integer nextStarredMatch;

    public class StarBinder extends Binder {
        StarManager getService() {
            return StarManager.this;
        }
    }

    @Override
    public void onCreate() {

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
                nextStarredMatch = getNextStarredMatch();
                notifyOfNewMatchIfNeeded();
            }
        }, new IntentFilter(Constants.MATCHES_UPDATED_ACTION));

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new StarBinder();

    public void notifyOfNewMatchPlayed(Match match, Integer matchesFromNow) {
        RemoteViews notificationRemoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.match_notification);

        notificationRemoteViews.setTextViewText(R.id.matchNotificationTitleTextView, "Don't miss Q" + match.number + ", starting in " + matchesFromNow.toString() + " matches!");
        notificationRemoteViews.setTextViewText(R.id.matchNumber, match.number.toString());

        List<Integer> teams = new ArrayList<>();
        teams.addAll(match.redAllianceTeamNumbers);
        teams.addAll(match.blueAllianceTeamNumbers);

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            notificationRemoteViews.setTextViewText(teamTextViewIDs[i], teams.get(i).toString());
        }

        notificationRemoteViews.setTextViewText(R.id.redScore, (match.redScore >= 0) ? match.redScore + "" : "???");
        notificationRemoteViews.setTextViewText(R.id.blueScore, (match.blueScore >= 0) ? match.blueScore + "" : "???");

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

    public void notifyOfNewMatchIfNeeded() {
        if (FirebaseLists.matchesList.getKeys().contains(nextStarredMatch.toString())) {
            if (nextStarredMatch - currentMatchNumber <= 3) {
                Match match = FirebaseLists.matchesList.getFirebaseObjectByKey(nextStarredMatch.toString());

                notifyOfNewMatchPlayed(match, nextStarredMatch - currentMatchNumber - 1);
            }
        }
    }

    public Integer getNextStarredMatch() {
        Integer nextStarredMatch = 0;

        List<Integer> starredMatches = ViewerApplication.getStarredMatches();
        Collections.reverse(starredMatches);
        for (Integer matchNumber : starredMatches) {
            if (matchNumber > currentMatchNumber) {
                nextStarredMatch = matchNumber;
            }
        }

        return nextStarredMatch;
    }
}
