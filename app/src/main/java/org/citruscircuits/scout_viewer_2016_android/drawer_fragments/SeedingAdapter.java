package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;

import org.citruscircuits.scout_viewer_2016_android.TeamValueComparator;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by colinunger on 1/28/16.
 */
public class SeedingAdapter extends RankingsAdapter {

    public SeedingAdapter(Context paramContext) {
        super(paramContext, new TeamValueComparator.TeamValueRetriever() {
            @Override
            public Integer retrieve(Team t) {
                return (Integer) Utils.getObjectField(t, "calculatedData.actualSeed");
            }
        }, false);
    }
}
