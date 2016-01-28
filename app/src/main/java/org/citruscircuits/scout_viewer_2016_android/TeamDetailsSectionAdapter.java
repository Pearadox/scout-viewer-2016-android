package org.citruscircuits.scout_viewer_2016_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applidium.headerlistview.SectionAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.drawer_fragments.RankingsFragment;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.Templates;

/**
 * Created by citruscircuits on 1/23/16.
 */
public class TeamDetailsSectionAdapter extends RankingsSectionAdapter {
    private String[][] fieldsToDisplay = {{"calculatedData.avgHighShotsAuto", "calculatedData.avgLowShotsAuto"}, {"calculatedData.avgHighShotsTele", "calculatedData.avgLowShotsTele"}, {"calculated.avgTimesCrossedDefensesAuto.a.cdf", "calculated.avgTimesCrossedDefensesAuto.a.pc", "calculated.avgTimesCrossedDefensesAuto.b.mt", "calculated.avgTimesCrossedDefensesAuto.b.rp", "calculated.avgTimesCrossedDefensesAuto.c.db", "calculated.avgTimesCrossedDefensesAuto.c.sp", "calculated.avgTimesCrossedDefensesAuto.d.rt", "calculated.avgTimesCrossedDefensesAuto.d.rw", "calculated.avgTimesCrossedDefensesAuto.lb.lb", "calculated.avgTimesCrossedDefensesTele.a.cdf", "calculated.avgTimesCrossedDefensesTele.a.pc", "calculated.avgTimesCrossedDefensesTele.b.mt", "calculated.avgTimesCrossedDefensesTele.b.rp", "calculated.avgTimesCrossedDefensesTele.c.db", "calculated.avgTimesCrossedDefensesTele.c.sp", "calculated.avgTimesCrossedDefensesTele.d.rt", "calculated.avgTimesCrossedDefensesTele.d.rw", "calculated.avgTimesCrossedDefensesAuto.lb.lb"}};

    private String[] sectionTitles = {"Auto", "Teleop", "Defenses"};
    private FirebaseList teams;
    private int teamNumber;
    private String[] defenseCategories = {"a", "b", "c", "d", "lb"};
    private ArrayList<ArrayList<String>> modifyableFieldsToDisplay;

    public TeamDetailsSectionAdapter(Context context, final int teamNumber) {
        super(context);
        this.teamNumber = teamNumber;
        teams = new FirebaseList(Constants.TEAMS_PATH, new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public Object getRowItem(int section, int row) {
        return fieldsToDisplay[section][row];
    }

    @Override
    public int numberOfSections() {
        return sectionTitles.length;
    }

    @Override
    public int numberOfRows(int section) {
        return fieldsToDisplay[section].length;
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }

    @Override
    public Object getSectionHeaderItem(int section) {
        return sectionTitles[section];
    }

    @Override
    public int getRankOfRowAndSection(final int section, final int row) {
        List<Team> sortedTeams = new ArrayList<>(teams.values());
        Collections.sort(sortedTeams, new TeamValueComparator(false, new TeamValueComparator.TeamValueRetriever() {
            @Override
            public Float retrieve(Team t) {
                return (Float)Utils.getObjectField(t, (String)getRowItem(section, row));
            }
        }));

        if (teams.containsKey(teamNumber + "")) {
            return sortedTeams.indexOf(teams.get(teamNumber + ""));
        } else {
            return -1;
        }
    }

    @Override
    public String getNameOfRowAndSection(int section, int row) {
        return Constants.KEYS_TO_TITLES.get(getRowItem(section, row));
    }

    @Override
    public Float getValueOfRowAndSection(int section, int row) {
        if (teams.containsKey(teamNumber + "")) {
            return (Float)Utils.getObjectField(teams.get(teamNumber + ""), (String)getRowItem(section, row));
        } else {
            return Float.valueOf(-1);
        }
    }
}