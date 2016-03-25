package org.citruscircuits.scout_viewer_2016_android.team_details;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;

import org.citruscircuits.scout_viewer_2016_android.Constants;
import org.citruscircuits.scout_viewer_2016_android.FirebaseLists;
import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.Utils;
import org.citruscircuits.scout_viewer_2016_android.ViewerApplication;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.citruscircuits.scout_viewer_2016_android.services.StarManager;

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
    BroadcastReceiver starReceiver;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_listview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        starReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(starReceiver, new IntentFilter(Constants.STARS_MODIFIED_ACTION));

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
        View teamDetailsHeaderView = teamDetailsHeaderListView.getChildAt(0);
        if (StarManager.isStarredTeam(teamNumber)) {
            teamDetailsHeaderView.setBackgroundColor(Constants.STAR_COLOR);
        } else {
            teamDetailsHeaderView.setBackgroundColor(Color.TRANSPARENT);
        }

        Team team = FirebaseLists.teamsList.getFirebaseObjectByKey(teamNumber.toString());

        TextView teamDetailsTeamNumberTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNumberTextView);
        teamDetailsTeamNumberTextView.setText(Utils.getDisplayValue(team, "number"));
        teamDetailsTeamNumberTextView.setOnLongClickListener(new StarLongClickListener());

        TextView teamDetailsTeamNameTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNameTextView);
        teamDetailsTeamNameTextView.setText(Utils.getDisplayValue(team, "name"));

        TextView teamDetailsSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeeding);
        teamDetailsSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.actualSeed")) ? Utils.roundDataPoint(team.calculatedData.actualSeed, 2, "???") : "???");

        TextView teamDetailsPredictedSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);
        teamDetailsPredictedSeedingTextView.setText((Utils.fieldIsNotNull(team, "calculatedData.predictedSeed")) ? Utils.roundDataPoint(team.calculatedData.predictedSeed, 2, "???") : "???");
    }

    public void reloadTeamImage() {
        try {
            File file = new File(getApplicationContext().getFilesDir(), "image_" + teamNumber.toString());
            //bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

            //make image fit screen, scale it by screen width divided by image width
            Bitmap tmpBitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            Float scale =  (float) metrics.widthPixels / (float)tmpBitmap.getWidth();
            Log.i("Team Image Scale", scale.toString());
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            bitmap = Bitmap.createBitmap(tmpBitmap, 0, 0,tmpBitmap.getWidth(), tmpBitmap.getHeight(), matrix, true);

            ImageView imageView = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(imageParams);
            imageView.setImageBitmap(bitmap);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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

    private class StarLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(75);
            TextView teamNumberTextView = (TextView)v;
            Integer teamNumber = Integer.parseInt(teamNumberTextView.getText().toString());
            if (StarManager.isStarredTeam(teamNumber)) {
                StarManager.removeStarredTeam(teamNumber);
            } else {
                StarManager.addStarredTeam(teamNumber);
            }
            reload();
            return true;
        }
    }
}
