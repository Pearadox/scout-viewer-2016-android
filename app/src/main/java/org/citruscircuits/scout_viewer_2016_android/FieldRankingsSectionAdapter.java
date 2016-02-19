package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.view.View;

/**
 * Created by colinunger on 2/6/16.
 */
public class FieldRankingsSectionAdapter extends RankingsSectionAdapter {
    public FieldRankingsSectionAdapter(Context context) {
        super(context);
    }

    @Override
    public String getRankTextOfRowInSection(int section, int row) {
        return null;
    }

    @Override
    public String getNameOfRowInSection(int section, int row) {
        return null;
    }

    @Override
    public String getValueOfRowInSection(int section, int row) {
        return null;
    }

    @Override
    public boolean isUnranked(int section, int row) {
        return false;
    }

    @Override
    public boolean isOtherTypeOfView(int section, int row) {
        return false;
    }

    @Override
    public View getOtherTypeOfView(int section, int row) {
        return null;
    }

    @Override
    public int numberOfSections() {
        return 0;
    }

    @Override
    public int numberOfRows(int section) {
        return 0;
    }

    @Override
    public Object getRowItem(int section, int row) {
        return null;
    }
}
