package cvora.chapter4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SimpleSpinnerView extends AppCompatActivity {

    String [] presidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_spinner_view);

        presidents = getResources().getStringArray(R.array.presidents_array);
        Spinner s1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,presidents);
        s1.setAdapter(adapter);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = parent.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),"You have selected item: "+presidents[index],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
