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
    public boolean secondaryFilter(Match value) {
        return value.redScore == -1 && value.blueScore == -1;
    }
}
