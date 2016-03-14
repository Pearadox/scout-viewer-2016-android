package org.citruscircuits.scout_viewer_2016_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Match;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.TeamInMatchData;
import org.citruscircuits.scout_viewer_2016_android.match_details.TeamInMatchDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 2/13/16.
 */
public class TeamInMatchDataGraphFragment extends GraphFragment {
    Integer teamNumber;
    String field;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamNumber = getArguments().getInt("team", 0);
        field = getArguments().getString("field");
        Log.i("SKDJNFIWNEUINDS", field);
    }

    @Override
    public void onResume() {
        super.onResume();

        barChart.setTouchEnabled(true);
        barChart.highlightValues(null);
    }

    @Override
    public List<String> getLabels() {
        List<String> matchNumbersStrings = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : Utils.getTeamInMatchDatasForTeamNumber(teamNumber)) {
            matchNumbersStrings.add(teamInMatchData.matchNumber.toString());
        }
        return matchNumbersStrings;
    }

    @Override
    public List<Float> getValues() {
        List<Float> dataValues = new ArrayList<>();
        for (TeamInMatchData teamInMatchData : Utils.getTeamInMatchDatasForTeamNumber(teamNumber)) {
            Object value =  Utils.getObjectField(teamInMatchData, field);
            if (value instanceof Integer) {
                dataValues.add(((Integer) value).floatValue());
            } else if (value instanceof Boolean) {
                dataValues.add((Boolean)value ? 1f : 0f);
            } else {
                dataValues.add((Float) value);
            }
        }

        return dataValues;
    }

    @Override
    public OnChartValueSelectedListener getOnChartValueSelectedListener() {
        return new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                barChart.setTouchEnabled(false);
                Integer matchNumberClicked = Integer.parseInt(getLabels().get(e.getXIndex()));
                Intent teamInMatchDataIntent = new Intent(getActivity(), TeamInMatchDetailsActivity.class);
                teamInMatchDataIntent.putExtra("team", teamNumber);
                teamInMatchDataIntent.putExtra("match", matchNumberClicked);
                getActivity().startActivity(teamInMatchDataIntent);
            }

            @Override
            public void onNothingSelected() {

            }
        };
    }
}
