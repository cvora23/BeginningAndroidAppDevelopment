package com.cricmybrain.chapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class RegisteringForUiEventsInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering_for_ui_events_in);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch(keyCode){

            case KeyEvent.KEYCODE_DPAD_CENTER:
                Toast.makeText(getBaseContext(),"Center was clicked",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                Toast.makeText(getBaseContext(),"Left Arrow was clicked",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                Toast.makeText(getBaseContext(),"Right Arrow was clicked",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_DPAD_UP:
                Toast.makeText(getBaseContext(),"Up Arrow was clicked",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                Toast.makeText(getBaseContext(),"Down Arrow was clicked",Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
