package app.free.chapter2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import app.free.chapter2.SimpleFragment1;

public class ActivityLoadingFragmentsDynamically extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // -- get the current display info
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        d.getMetrics(displaymetrics);
        if(displaymetrics.widthPixels < displaymetrics.heightPixels){
            // --landscape mode
            SimpleFragment1 fragment1 = new SimpleFragment1();
            // android.R.id.content refers to the content view of the activity
            fragmentTransaction.replace(android.R.id.content,fragment1);
        }else{
            // --portrait mode
            SimpleFragment2 fragment2 = new SimpleFragment2();
            fragmentTransaction.replace(android.R.id.content,fragment2);
        }
        fragmentTransaction.commit();
    }
}
