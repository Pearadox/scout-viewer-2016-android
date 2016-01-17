package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class ScheduleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Match> matches = new ArrayList<>();
    private ArrayList<String> keys = new ArrayList<>();

    public ScheduleAdapter(Context paramContext) {
        context = paramContext;

        Firebase scheduleRef = new Firebase("https://1678-dev-2016.firebaseio.com/Matches/");
        scheduleRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                matches.add(dataSnapshot.getValue(Match.class));
                keys.add(dataSnapshot.getKey());
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int index = keys.indexOf(s);
                matches.set(index, dataSnapshot.getValue(Match.class));
                keys.set(index, dataSnapshot.getKey());
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                int index = matches.indexOf(dataSnapshot.getKey());
                matches.remove(index);
                keys.remove(index);
                notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;


        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.schedule_cell, parent, false);
        }

        Match match = (Match)getItem(position);

        TextView matchTextView = (TextView)rowView.findViewById(R.id.matchNumber);
        matchTextView.setText(match.number);

        int[] teams = new int[6];
        System.arraycopy(match.redAllianceTeamNumbers, 0, teams, 0, 3);
        System.arraycopy(match.blueAllianceTeamNumbers, 0, teams, 3, 3);

        int[] teamTextViewIDs = {R.id.teamOne, R.id.teamTwo, R.id.teamThree, R.id.teamFour, R.id.teamFive, R.id.teamSix};
        for (int i = 0; i < 6; i++) {
            TextView teamTextView = (TextView)rowView.findViewById(teamTextViewIDs[i]);
            teamTextView.setText(teams[i] + "");
        }

        TextView redScoreTextView = (TextView)rowView.findViewById(R.id.redScore);
        redScoreTextView.setText((match.redScore >= 0) ? match.redScore + "" : "???");

        TextView blueScoreTextView = (TextView)rowView.findViewById(R.id.blueScore);
        blueScoreTextView.setText((match.blueScore >= 0) ? match.blueScore + "" : "???");

        return rowView;
    }
}
