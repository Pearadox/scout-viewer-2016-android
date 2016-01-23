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

    @Override
    public boolean secondaryFilter(Match value) {
        return value.redScore > -1 && value.blueScore > -1;
    }
}
