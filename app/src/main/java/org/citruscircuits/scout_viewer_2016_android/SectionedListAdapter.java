package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;

/**
 * Created by citruscircuits on 1/23/16.
 */
public abstract class SectionedListAdapter extends BaseAdapter {
    private Context context;
    private Object[] viewContents;
    private ArrayList<Integer> separatorIndices;

    public SectionedListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return viewContents.length;
    }

    @Override
    public Object getItem(int position) {
        return viewContents[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView;
        if (separatorIndices.contains(Integer.valueOf(position))) {
            rowView = inflater.inflate(R.layout.search_header, parent, false);
            TextView separatorTextView = (TextView)rowView.findViewById(R.id.separatorTextView);
            separatorTextView.setText((String)getItem(position));
        } else {
            rowView = getViewFromData(getItem(position));
        }

        return rowView;
    }

    public abstract View getViewFromData(Object data);
}
