package com.cricmybrain.chapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class DetectOrientationChanges extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_orientation_changes);

        WindowManager wm = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
       wm.getDefaultDisplay().getMetrics(metrics);
        if(metrics.widthPixels > metrics.heightPixels){
            Log.d("Orientation","Landscape Mode");
        }else{
            Log.d("Orientation","Portrait Mode");
        }

    }
}
