package org.citruscircuits.scout_viewer_2016_android.drawer_fragments.abstract_classes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import org.citruscircuits.scout_viewer_2016_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by colinunger on 2/7/16.
 */
public abstract class SearchView extends RelativeLayout {

    private String[] scopes;
    private String selectedScope;
    private String searchString = "";
    private List<Button> scopeButtons = new ArrayList<>();

    public SearchView(Context context, String[] scopes) {
        super(context);
        this.scopes = scopes;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_header, this, true);

        final EditText searchField = (EditText)findViewById(R.id.listSearchEditText);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchString = s.toString();
                search(searchString, selectedScope);
            }
        });

        LinearLayout scopeButtonsLinearLayout = (LinearLayout)findViewById(R.id.scopeButtonsLinearLayout);
        if (scopes.length > 1) {
            for (String scope : scopes) {
                Button scopeButton = new Button(context);
                scopeButton.setText(scope);
                scopeButton.setTextColor(Color.BLACK);
                scopeButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                scopeButtonsLinearLayout.addView(scopeButton);
                scopeButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        button.setBackgroundColor(Color.argb(255, 255, 255, 255));
                        selectedScope = button.getText().toString();

                        for (Button otherButton : scopeButtons) {
                            if (otherButton.getText().toString() != selectedScope) {
                                otherButton.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }

                        search(searchString, selectedScope);
                    }
                });
                scopeButtons.add(scopeButton);
            }
        }
        selectScope(0);
        search(searchString, selectedScope);
    }

    private void selectScope(int scopeIndex) {
        selectedScope = scopes[scopeIndex];
        if (scopes.length > 1) {
            scopeButtons.get(scopeIndex).callOnClick();
        }
    }

    public abstract void search(String searchString, String scope);
}
