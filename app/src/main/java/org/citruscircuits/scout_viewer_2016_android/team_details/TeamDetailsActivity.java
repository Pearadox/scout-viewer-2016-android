package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class TeamDetailsActivity extends ActionBarActivity {

    LinearLayout headerPhotoLinearLayout;
    Integer teamNumber;
    BroadcastReceiver teamUpdatedReceiver;
    BroadcastReceiver photoDownloadedReceiver;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_listview);
        teamNumber = getIntent().getIntExtra("teamNumber", 1678);
        Log.e("test", "Team number is " + teamNumber);

        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));
        View teamDetailsHeaderView = getLayoutInflater().inflate(R.layout.team_details_header, null);
        teamDetailsHeaderListView.getListView().addHeaderView(teamDetailsHeaderView, null, false);

        teamUpdatedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(teamUpdatedReceiver, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));


        photoDownloadedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Integer intentTeamNumber = intent.getIntExtra("teamNumber", 0);
                Log.e("test", "Got the broadcast for team " + intentTeamNumber.toString() + ", but my team number is " + teamNumber.toString());
                if (intentTeamNumber.equals(teamNumber)) {
                    Log.e("test", "AND NOW IM GOING TO ACTUALLY RELOAD");
                    reloadTeamImage();
                }
            }
        };

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(photoDownloadedReceiver, new IntentFilter(Constants.NEW_TEAM_PHOTO_ACTION));

        headerPhotoLinearLayout = (LinearLayout)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamPhotoLinearLayout);

        reload();
        reloadTeamImage();
    }

    public void reload() {
        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));
        View teamDetailsHeaderView = teamDetailsHeaderListView.getChildAt(0);

        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());

        TextView teamDetailsTeamNumberTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNumberTextView);
        teamDetailsTeamNumberTextView.setText(team.number.toString());

        TextView teamDetailsTeamNameTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNameTextView);
        teamDetailsTeamNameTextView.setText(team.name.toString());

        TextView teamDetailsSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeeding);
        teamDetailsSeedingTextView.setText(team.calculatedData.actualSeed.toString());

//        TextView teamDetailsSeedingRPsTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeedingRPs);
//        teamDetailsSeedingRPsTextView.setText(team.calculatedData.numRPs.toString());

        TextView teamDetailsPredictedSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);
        teamDetailsPredictedSeedingTextView.setText(team.calculatedData.predictedSeed.toString());

//        TextView teamDetailsPredictedSeedingRPsTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeedingRPs);
//        teamDetailsPredictedSeedingRPsTextView.setText(team.calculatedData.predictedNumRPs.toString());
    }

    public void reloadTeamImage() {
        try {
            File file = new File(getApplicationContext().getFilesDir(), "image_" + teamNumber.toString());
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(bitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            headerPhotoLinearLayout.addView(imageView, params);
        } catch (Exception e) {
            Log.e("test", "ERROR: " + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(teamUpdatedReceiver);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(photoDownloadedReceiver);

        if (bitmap != null) {
            bitmap.recycle();
        }
    }
}
