package org.citruscircuits.scout_viewer_2016_android;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;

import java.util.Comparator;

/**
 * Created by citruscircuits on 1/17/16.
 */
public class MatchNumberComparator implements Comparator<Match> {

    private boolean isNotReversed;

    public MatchNumberComparator(boolean notReverse) {
        isNotReversed = notReverse;
    }

    public int compare(Match m1, Match m2) {
        return ((isNotReversed) ? 1 : -1) * Integer.valueOf(m1.number).compareTo(Integer.valueOf(m2.number));
    }
}
