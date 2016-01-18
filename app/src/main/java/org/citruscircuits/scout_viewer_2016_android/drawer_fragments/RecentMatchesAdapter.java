package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.citruscircuits.scout_viewer_2016_android.MainActivity;
import org.citruscircuits.scout_viewer_2016_android.MatchNumberComparator;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.Collections;
import java.util.List;


/**
 * Created by citruscircuits on 1/17/16.
 */
public class RecentMatchesAdapter extends MatchesAdapter {


    public RecentMatchesAdapter(Context paramContext) {
        super(paramContext, new MatchNumberComparator(false));
    }

//    public void notifyOfNewMatchPlayed(Match match) {
//        RemoteViews notificationRemoteViews = new RemoteViews(context.getPackageName(), R.layout.match_notification);
//
//        notificationRemoteViews.setTextViewText(R.id.matchNumber, match.number);
//
//        int[] teams = new int[6];
//        System.arraycopy(match.redAllianceTeamNumbers, 0, teams, 0, 3);
//        System.arraycopy(match.blueAllianceTeamNumbers, 0, teams, 3, 3);
//
//        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
//        for (int i = 0; i < 6; i++) {
//            notificationRemoteViews.setTextViewText(teamTextViewIDs[i], teams[i] + "");
//        }
//
//        notificationRemoteViews.setTextViewText(R.id.redScore, (match.redScore >= 0) ? match.redScore + "" : "???");
//        notificationRemoteViews.setTextViewText(R.id.blueScore, (match.blueScore >= 0) ? match.blueScore + "" : "???");
//
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(R.drawable.ic_launcher)
//                        .setContent(notificationRemoteViews);
//        // Creates an explicit intent for an Activity in your app
//        Intent resultIntent = new Intent(context, MainActivity.class);
//
//        int mId = 1678;
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//// Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(MainActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
////mId allows you to update the notification later on.
//        mNotificationManager.notify(mId, mBuilder.build());
//    }

    @Override
    public boolean filter(Match value) {
        return (value.redScore > -1 && value.blueScore > -1);
    }
}
