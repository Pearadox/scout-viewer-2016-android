package org.citruscircuits.scout_viewer_2016_android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.citruscircuits.scout_viewer_2016_android.R;
import org.citruscircuits.scout_viewer_2016_android.SeparatedListAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TeamDetailsActivity extends ActionBarActivity {

    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";

    public Map<String,?> createItem(String title, String caption) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CAPTION, caption);
        return item;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        List<Map<String,?>> security = new LinkedList<Map<String,?>>();
        security.add(createItem("Remember passwords", "Save usernames and passwords for Web sites"));
        security.add(createItem("Clear passwords", "Save usernames and passwords for Web sites"));
        security.add(createItem("Show security warnings", "Show warning if there is a problem with a site's security"));

        // create our list and custom adapter
        SeparatedListAdapter adapter = new SeparatedListAdapter(this);
        adapter.addSection("Array test", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[] { "First item", "Item two" }));
        adapter.addSection("Security", new SimpleAdapter(this, security, R.layout.list_complex,
                new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

        ListView list = new ListView(this);
        list.setAdapter(adapter);
        this.setContentView(list);

    }

}
