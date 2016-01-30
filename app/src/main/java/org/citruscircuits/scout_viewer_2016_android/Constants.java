package org.citruscircuits.scout_viewer_2016_android;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by citruscircuits on 1/16/16.
 */
public class Constants {
    public static final String[] DRAWER_TITLES = {"Recent Matches", "Upcoming Matches", "Schedule", "Seeding", "Predicted Seeding", "First Pick", "Second Pick"};
    public static final String MATCHES_PATH = "https://1678-dev-2016.firebaseio.com/Matches";
    public static final String TEAMS_PATH = "https://1678-dev-2016.firebaseio.com/Teams";
    public static final String TEAMS_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.teamsupdated";
    public static final String MATCHES_UPDATED_ACTION = "org.citruscircuits.scout_viewer_2016_android.matchesupdated";

    public static final Map<String, String> KEYS_TO_TITLES = new HashMap<String, String> () {
        {
            put("calculatedData.avgHighShotsAuto", "Avg. Auto High Shots");
            put("calculatedData.avgLowShotsAuto", "Avg. Auto Low Shots");
            put("calculatedData.avgHighShotsTele", "Avg. Tele High Shots");
            put("calculatedData.avgLowShotsTele", "Avg. Tele Low Shots");
        }

    };
}
