package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;

import org.citruscircuits.scout_viewer_2016_android.TeamValueComparator;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

/**
 * Created by citruscircuits on 1/27/16.
 */
public class FirstPickAbilityAdapter extends RankingsAdapter {

    public FirstPickAbilityAdapter(Context paramContext) {
        super(paramContext, new TeamValueComparator.TeamValueRetriever() {
            @Override
            public Float retrieve(Team t) {
                return (Float) Utils.getObjectField(t, "calculatedData.firstPickAbility");
            }
        }, false);
    }
}
