package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;

import org.citruscircuits.scout_viewer_2016_android.TeamValueComparator;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by citruscircuits on 1/18/16.
 */
public class SiegePowerAdapter extends RankingsAdapter {

    public SiegePowerAdapter(Context paramContext) {
        super(paramContext, new TeamValueComparator.TeamValueRetriever() {
            @Override
            public Float retrieve(Team t) {
                return Float.valueOf(t.calculatedData.avgBallControl);
            }
        }, false);
    }
}