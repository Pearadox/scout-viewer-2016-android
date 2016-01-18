package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.citruscircuits.scout_viewer_2016_android.MatchNumberComparator;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class ScheduleAdapter extends MatchesAdapter {

    public ScheduleAdapter(Context paramContext) {
        super(paramContext);
    }

    @Override
    public void setupFirebaseListening() {
        Firebase scheduleRef = new Firebase("https://1678-dev-2016.firebaseio.com/Matches/");
        scheduleRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                matches.add(dataSnapshot.getValue(Match.class));
                keys.add(dataSnapshot.getKey());
                Collections.sort(matches, new MatchNumberComparator(true));
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int index = keys.indexOf(dataSnapshot.getKey());
                if (index > -1) {
                    matches.set(index, dataSnapshot.getValue(Match.class));
                    keys.set(index, dataSnapshot.getKey());
                    Collections.sort(matches, new MatchNumberComparator(true));
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int index = matches.indexOf(dataSnapshot.getKey());
                matches.remove(index);
                keys.remove(index);
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
