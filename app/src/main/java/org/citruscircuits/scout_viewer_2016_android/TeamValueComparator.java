package org.citruscircuits.scout_viewer_2016_android;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.Comparator;

/**
 * Created by citruscircuits on 1/18/16.
 */
public class TeamValueComparator implements Comparator<Team> {
    private boolean isNotReversed;
    private TeamValueRetriever retrievalFunction;

    public interface TeamValueRetriever {
        Comparable retrieve(Team t);
    }

    public TeamValueComparator(boolean notReverse, TeamValueRetriever retrievalFunction) {
        isNotReversed = notReverse;
        this.retrievalFunction = retrievalFunction;
    }

    @Override
    public int compare(Team t1, Team t2) {
        return ((isNotReversed) ? 1 : -1) * retrievalFunction.retrieve(t1).compareTo(retrievalFunction.retrieve(t2));
    }
}
