package org.citruscircuits.scout_viewer_2016_android;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.applidium.headerlistview.HeaderListView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.firebase_classes.Team;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TeamDetailsActivity extends ActionBarActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        int teamNumber = getIntent().getIntExtra("teamNumber", 1678);


        final HeaderListView teamDetailsHeaderListView = (HeaderListView)findViewById(R.id.teamDetailsHeaderListView);
        teamDetailsHeaderListView.setAdapter(new TeamDetailsSectionAdapter(this, teamNumber));
        View teamDetailsHeaderView = getLayoutInflater().inflate(R.layout.team_details_header, null);
        teamDetailsHeaderListView.addHeaderView(teamDetailsHeaderView);

        final TextView teamDetailsTeamNumberTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNumberTextView);
        final TextView teamDetailsTeamNameTextView = (TextView)teamDetailsHeaderView.findViewById(R.id.teamDetailsTeamNameTextView);
        final TextView teamDetailsSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsSeeding);
        final TextView teamDetailsPredictedSeedingTextView = (TextView)teamDetailsHeaderListView.findViewById(R.id.teamDetailsPredictedSeeding);


        Firebase teamRef = new Firebase(Constants.TEAMS_PATH + "/" + teamNumber);
        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.getValue(Team.class);
                teamDetailsTeamNumberTextView.setText(team.number + "");
                teamDetailsTeamNameTextView.setText(team.name);
//                teamDetailsSeedingTextView.setText(team.calculatedData.seed);
//                teamDetailsPredictedSeedingTextView.setText(team.calculatedData.predictedSeed);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        imageView = (ImageView)teamDetailsHeaderView.findViewById(R.id.teamDetailsImageView);
        new TeamImageDownloadAsyncTask().execute();
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
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(bitmap);
            imageView.setMaxHeight(bitmap.getHeight());
//            imageView.set
        }
    }
}
