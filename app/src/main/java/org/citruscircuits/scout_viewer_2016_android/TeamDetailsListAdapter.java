package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.lang.reflect.Field;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class TeamDetailsListAdapter extends SectionedListAdapter {
    private Team team;
    private String[] fieldsToDisplay = {"avgHighShotsAuto", "avgLowShotsAuto", "avgHighShotsTele", "avgLowShotsTele"};


    public TeamDetailsListAdapter(Context context, int teamNumber) {
        super(context);

        Firebase teamRef = new Firebase(Constants.TEAMS_PATH + "/" + teamNumber);
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                team = dataSnapshot.getValue(Team.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // Do nothing
            }
        });
    }


    @Override
    public View getViewFromData(Object data) {
        return null;
    }
}
