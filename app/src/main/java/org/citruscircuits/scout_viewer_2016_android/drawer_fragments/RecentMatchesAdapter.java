package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.citruscircuits.scout_viewer_2016_android.MainActivity;
import org.citruscircuits.scout_viewer_2016_android.MatchNumberComparator;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.Collections;


/**
 * Created by citruscircuits on 1/17/16.
 */
public class RecentMatchesAdapter extends MatchesAdapter {


    public RecentMatchesAdapter(Context paramContext) {
        super(paramContext);
    }

    @Override
    public void setupFirebaseListening() {
        Firebase scheduleRef = new Firebase("https://1678-dev-2016.firebaseio.com/Matches/");
        scheduleRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Match match = dataSnapshot.getValue(Match.class);
                if (match.redScore > -1 && match.blueScore > -1) {
                    matches.add(0, dataSnapshot.getValue(Match.class));
                    keys.add(0, dataSnapshot.getKey());
                    Collections.sort(matches, new MatchNumberComparator(false));
                    Collections.sort(keys);
                    Collections.reverse(keys);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Match match = dataSnapshot.getValue(Match.class);
                int index = keys.indexOf(dataSnapshot.getKey());
                if (match.redScore > -1 && match.blueScore > -1) {
                    if (index < 0) {
                        matches.add(0, dataSnapshot.getValue(Match.class));
                        keys.add(0, dataSnapshot.getKey());
                        Collections.sort(matches, new MatchNumberComparator(false));
                        Collections.sort(keys);
                        Collections.reverse(keys);
                        notifyDataSetChanged();
                    } else {
                        matches.set(index, dataSnapshot.getValue(Match.class));
                        keys.set(index, dataSnapshot.getKey());
                        Collections.sort(matches, new MatchNumberComparator(false));
                        Collections.sort(keys);
                        Collections.reverse(keys);
                        notifyDataSetChanged();
                    }
                } else {
                    if (index > -1) {
                        matches.remove(index);
                        keys.remove(index);
                        notifyDataSetChanged();
                        notifyOfNewMatchPlayed();
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int index = matches.indexOf(dataSnapshot.getKey());
                if (index > -1) {
                    matches.remove(index);
                    keys.remove(index);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void notifyOfNewMatchPlayed() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
//        NotificationManager mNotificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
//        mNotificationManager.notify(mId, mBuilder.build());
    }
}
