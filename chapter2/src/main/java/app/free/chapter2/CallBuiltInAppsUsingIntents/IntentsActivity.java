package app.free.chapter2.CallBuiltInAppsUsingIntents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.free.chapter2.R;

public class IntentsActivity extends Activity {

    int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
    }

    public void onClickWebBrowser(View view){
        Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"));
        startActivity(i);
    }
    public void onClickMakeCalls(View view){
        Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+651234567"));
        startActivity(i);
    }
    public void onClickShowMap(View view){

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
        startActivity(intent);
    }

    public void onClickLaunchMyBrowser(View view){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://www.amazon.com"));
        i.addCategory("app.free.chapter2.IntentFiltersExample.BrowserActivityWrong");
        startActivity(Intent.createChooser(i,"Open URL using...."));
    }
}
