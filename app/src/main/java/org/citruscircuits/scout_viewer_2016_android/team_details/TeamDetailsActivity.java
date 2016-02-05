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
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class TeamDetailsActivity extends ActionBarActivity {

    LinearLayout headerPhotoLinearLayout;
    Integer teamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_listview);
        teamNumber = getIntent().getIntExtra("teamNumber", 1678);

        HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));
        View teamDetailsHeaderView = getLayoutInflater().inflate(R.layout.team_details_header, null);
        teamDetailsHeaderListView.getListView().addHeaderView(teamDetailsHeaderView, null, false);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                reload();
            }
        }, new IntentFilter(Constants.TEAMS_UPDATED_ACTION));

        headerPhotoLinearLayout = (LinearLayout)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamPhotoLinearLayout);
        new TeamImageDownloadAsyncTask().execute();
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

        TextView teamDetailsSeedingRPsTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeedingRPs);
        teamDetailsSeedingRPsTextView.setText(team.calculatedData.numRPs.toString());

        TextView teamDetailsPredictedSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);
        teamDetailsPredictedSeedingTextView.setText(team.calculatedData.predictedSeed.toString());

        TextView teamDetailsPredictedSeedingRPsTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeedingRPs);
        teamDetailsPredictedSeedingRPsTextView.setText(team.calculatedData.predictedNumRPs.toString());
    }

    private class TeamImageDownloadAsyncTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                URL url = new URL("https://dl.dropboxusercontent.com/u/63662632/1678.jpeg");
                Log.e("test", "Input stream is " + url.openStream().toString());
                InputStream in = new BufferedInputStream(url.openStream());
                Log.e("test", in.toString());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Bitmap image = BitmapFactory.decodeStream(in, new Rect(0, 0, 0, 0), options);
                in.close();
                return image;
            } catch (MalformedURLException e) {
                Log.e("error", "Exception: " + e.getMessage());
                return null;
            } catch (IOException ioe) {
                Log.e("error", "io Exception: " + ioe.getMessage());
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
            Bitmap bitmap = (Bitmap)o;
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(bitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            headerPhotoLinearLayout.addView(imageView, params);
        }
    }
}
