package org.citruscircuits.scout_viewer_2016_android.drawer_fragments;

import android.content.Context;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.citruscircuits.scout_viewer_2016_android.MatchNumberComparator;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class ScheduleAdapter extends MatchesAdapter {
    public ScheduleAdapter(Context paramContext) {
        super(paramContext, new MatchNumberComparator(true));
    }

    @Override
    public boolean secondaryFilter(Match value) {
        return true;
    }
}
