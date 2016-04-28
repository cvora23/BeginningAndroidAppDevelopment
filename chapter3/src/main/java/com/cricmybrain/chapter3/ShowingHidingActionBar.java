package com.cricmybrain.chapter3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowingHidingActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_hiding_action_bar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
    }
}
