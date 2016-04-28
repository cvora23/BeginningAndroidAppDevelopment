package com.cricmybrain.chapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ActivityLifeCycleWithOrientationChanges extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_life_cycle_with_orientation_changes);
        Log.d("StateInfo", "onCreate");
        String str = (String)getLastCustomNonConfigurationInstance();
        if(str != null)
            Log.d("StateInfo onCreate-str:", str);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("ID","1234567890");
        super.onSaveInstanceState(outState);
        Log.d("StateInfo","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String ID = savedInstanceState.getString("ID");
        Log.d("StateInfo","onRestoreInstanceState");
        Log.d("StateInfo ID Value is: ",ID);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return ("Some Text to preserve");
    }

    @Override
    public void onStart() {
        Log.d("StateInfo", "onStart");
        super.onStart();
    }
    @Override
    public void onResume() {
        Log.d("StateInfo", "onResume");
        super.onResume();
    }
    @Override
    public void onPause() {
        Log.d("StateInfo", "onPause");
        super.onPause();
    }
    @Override
    public void onStop() {
        Log.d("StateInfo", "onStop");
        super.onStop();
    }
    @Override
    public void onDestroy() {
        Log.d("StateInfo", "onDestroy");
        super.onDestroy();
    }
    @Override
    public void onRestart() {
        Log.d("StateInfo", "onRestart");
        super.onRestart();
    }

}
