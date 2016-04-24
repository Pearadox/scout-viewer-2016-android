package org.citruscircuits.scout_viewer_2016_android.match_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.ViewerActivity;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.team_details.TeamDetailsSectionAdapter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by colinunger on 1/31/16.
 */

public class TeamInMatchDetailsActivity extends ViewerActivity {

    Integer teamNumber;
    Integer matchNumber;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_section_listview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        teamNumber = getIntent().getIntExtra("team", 1678);
        matchNumber = getIntent().getIntExtra("match", 1);
        setTitle("Team " + teamNumber + " In Match " + matchNumber + " Details");

        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new TeamInMatchDetailsSectionAdapter(this, teamNumber, matchNumber));
    }
}

