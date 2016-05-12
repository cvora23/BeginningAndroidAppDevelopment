package cvora.chapter6_datapersistence;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsingPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_preferences);
    }

    public void onClickLoad(View view){
        Intent i = new Intent("cvora.chapter6_datapersistence.AppPreferenceActivity");
        startActivity(i);
    }

    public void onClickDisplay(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("appPreferences",MODE_PRIVATE);
        DisplayText(sharedPreferences.getString("editTextPref",""));
    }

    public void onClickModify(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("appPreferences",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("editTextPref",((EditText)findViewById(R.id.txtString)).getText().toString());
        prefsEditor.commit();
    }

    private void DisplayText(String str){
        Toast.makeText(getBaseContext(),str,Toast.LENGTH_SHORT).show();
    }

}
