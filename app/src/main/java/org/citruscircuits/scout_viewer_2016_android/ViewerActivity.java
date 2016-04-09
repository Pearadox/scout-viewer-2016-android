package org.citruscircuits.scout_viewer_2016_android;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;

public class ViewerActivity extends ActionBarActivity {
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }
}
