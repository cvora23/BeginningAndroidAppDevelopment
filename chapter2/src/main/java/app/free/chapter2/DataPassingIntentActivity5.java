package app.free.chapter2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DataPassingIntentActivity5 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_passing_intent5);

        // get data passed in using getStringExtra
        Toast.makeText(this,getIntent().getStringExtra("str1"),Toast.LENGTH_LONG).show();

        // get data passe in using getIntExtra
        Toast.makeText(this,Integer.toString(getIntent().getIntExtra("age1",0)),Toast.LENGTH_LONG).show();

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this,bundle.getString("str2"),Toast.LENGTH_LONG).show();

        Toast.makeText(this,Integer.toString(bundle.getInt("age2")),Toast.LENGTH_LONG).show();
    }

    public void onClick(View view){

        Intent i = new Intent();
        i.putExtra("age3",45);
        i.setData(Uri.parse("Something passed back to main activity"));
        setResult(RESULT_OK, i);

        finish();

    }
}
