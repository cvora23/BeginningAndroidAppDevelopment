package com.cricmybrain.chapter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisteringForUiEventsInViews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering_for_ui_events_in_views);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(btnListener);

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(btnListener);

        EditText txt1 = (EditText)findViewById(R.id.txt1);
        txt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(getBaseContext(),((EditText)v).getId() + " has focus - "+ hasFocus,Toast.LENGTH_SHORT).show();
            }
        });

    }

    // -- create an anonymous class to act as button click listener
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getBaseContext(),((Button)v).getText()+" was clicked",Toast.LENGTH_SHORT).show();
        }
    };




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
