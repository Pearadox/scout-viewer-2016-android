package org.citruscircuits.scout_viewer_2016_android;

import android.util.Log;

import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.Comparator;

/**
 * Created by colinunger on 1/30/16.
 */
public class ObjectFieldComparator<T extends Comparable> implements Comparator {
    private boolean isNotReversed;
    private String field;

    public ObjectFieldComparator(String field, boolean notReverse) {
        isNotReversed = notReverse;
        this.field = field;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Integer reversed = (isNotReversed) ? 1 : -1;
        T value1 = (T) Utils.getObjectField(o1, field);
        T value2 = (T) Utils.getObjectField(o2, field);
        if (value1 == null && value2 != null) {
            return 1;
        } else if (value1 != null && value2 == null) {
            return -1;
        } else if (value1 == null) {
            return 0;
        } else {
            return reversed * value1.compareTo(value2);
        }
        /*try {

            return ((isNotReversed) ? 1 : -1) * value1.compareTo(value2);
        } catch (NullPointerException npe) {
            return 0;
        }*/
    }
}
