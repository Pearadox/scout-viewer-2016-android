package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.ObjectFieldComparator;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.List;

/**
 * Created by citruscircuits on 1/18/16.
 */
public abstract class RankingsAdapter<T> extends SearchableFirebaseListAdapter<T> {
    private String rankFieldName;
    private String valueFieldName;

    public RankingsAdapter(Context context, String rankFieldName, String valueFieldName, boolean isNotReversed) {
        super(context, new ObjectFieldComparator(rankFieldName, isNotReversed));
        this.rankFieldName = rankFieldName;
        this.valueFieldName = valueFieldName;
    }

    @Override
    public int getCount() {
        return filteredValues.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredValues.get(position);
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
            rowView = inflater.inflate(R.layout.ranking_cell, parent, false);
        }

        T value = (T)getItem(position);

        TextView rankingTextView = (TextView)rowView.findViewById(R.id.rankingTextView);
        rankingTextView.setText(filteredValues.indexOf(value) + 1 + "");

        TextView teamNumberTextView = (TextView)rowView.findViewById(R.id.teamNumberTextView);
        if (searchString.length() > 0) {
            teamNumberTextView.setText(Utils.highlightTextInString(getRankCellText(value), searchString));
        } else {
            teamNumberTextView.setText(getRankCellText(value));
        }

        TextView valueTextView = (TextView)rowView.findViewById(R.id.valueTextView);
        valueTextView.setText(Utils.roundDataPoint(Utils.getObjectField(value, valueFieldName), 2));

        return rowView;
    }


    public abstract String getRankCellText(T value);

}
