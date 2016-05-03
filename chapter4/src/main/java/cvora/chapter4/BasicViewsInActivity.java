package cvora.chapter4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BasicViewsInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_views_in);


        Button btnOpen= (Button)findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayToast("You have clicked Open button");
            }
        });

        Button btnSave= (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayToast("You have clicked Save button");
            }
        });

        CheckBox checkBox = (CheckBox)findViewById(R.id.chkAutoSave);
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    DisplayToast("CheckBox is checked");
                }else{
                    DisplayToast("CheckBox is unchecked");
                }
            }
        });

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rdbGrp1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton)findViewById(R.id.rdb1);
                if(rb1.isChecked()){
                    DisplayToast("Option 1 checked !!");
                }else{
                    DisplayToast("Option 2 checked !!");
                }
            }
        });

        ToggleButton toggleButton = (ToggleButton)findViewById(R.id.toggle1);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ToggleButton)v).isChecked()){
                    DisplayToast("Toggle Button is ON");
                }else{
                    DisplayToast("Toggle Button is OFF");
                }
            }
        });

    }

    private void DisplayToast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
