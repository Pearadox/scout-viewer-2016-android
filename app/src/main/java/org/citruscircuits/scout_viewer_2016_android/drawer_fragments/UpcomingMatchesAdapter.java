package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.MatchNumberComparator;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class UpcomingMatchesAdapter extends MatchesAdapter {

    public UpcomingMatchesAdapter(Context paramContext) {
        super(paramContext, new MatchNumberComparator(true));
    }

    @Override
    public boolean filter(Match value) {
        return (value.redScore == -1 && value.blueScore == -1);
    }

//    @Override
//    public void setupFirebaseListening() {
//        Firebase scheduleRef = new Firebase("https://1678-dev-2016.firebaseio.com/Matches/");
//        scheduleRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Match match = dataSnapshot.getValue(Match.class);
//                if (match.redScore == -1 && match.blueScore == -1) {
//                    matches.add(dataSnapshot.getValue(Match.class));
//                    keys.add(dataSnapshot.getKey());
//                    sortMatchesAndKeys();
//                    notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Match match = dataSnapshot.getValue(Match.class);
//                int index = keys.indexOf(dataSnapshot.getKey());
//                if (match.redScore == -1 && match.blueScore == -1) {
//                    if (index < 0) {
//                        matches.add(dataSnapshot.getValue(Match.class));
//                        keys.add(dataSnapshot.getKey());
//                        sortMatchesAndKeys();
//                        notifyDataSetChanged();
//                    } else {
//                        matches.set(index, dataSnapshot.getValue(Match.class));
//                        keys.set(index, dataSnapshot.getKey());
//                        sortMatchesAndKeys();
//                        notifyDataSetChanged();
//                    }
//                } else {
//                    if (index > -1) {
//                        matches.remove(index);
//                        keys.remove(index);
//                        notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                int index = matches.indexOf(dataSnapshot.getKey());
//                if (index > -1) {
//                    matches.remove(index);
//                    keys.remove(index);
//                    notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }
//
//    public void sortMatchesAndKeys() {
//        Collections.sort(matches, new MatchNumberComparator(true));
//        Collections.sort(keys);
//    }

}
