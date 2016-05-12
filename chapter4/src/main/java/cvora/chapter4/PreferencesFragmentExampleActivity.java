package cvora.chapter4;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PreferencesFragmentExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_fragment_example);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PreferenceFragment1 fragment1 = new PreferenceFragment1();
        fragmentTransaction.replace(android.R.id.content,fragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
