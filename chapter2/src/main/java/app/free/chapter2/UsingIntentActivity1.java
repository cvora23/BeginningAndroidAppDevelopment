package app.free.chapter2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UsingIntentActivity1 extends Activity {

    int request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_intent1);
    }

    public void onClick(View view){
        startActivity(new Intent("app.free.chapter2.UsingIntentActivity2"));
    }

    public void onClick4(View view){
        startActivityForResult(
                new Intent("app.free.chapter2.UsingIntentActivity4"),
                request_code);
    }

    public void onClick5(View view){

        Intent i = new Intent("app.free.chapter2.DataPassingIntentActivity5");
        // putExtra to add new/value pairs
        i.putExtra("str1","This is a string");
        i.putExtra("age1",25);
        // use a Bundle object to add new/values
        Bundle extras = new Bundle();
        extras.putString("str2","This is another string");
        extras.putInt("age2", 35);
        i.putExtras(extras);
        startActivityForResult(i,request_code+1);

    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == request_code){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,data.getData().toString(),Toast.LENGTH_LONG).show();
            }
        }else if(requestCode == request_code+1 && resultCode == RESULT_OK){
            // get result using getIntExtra
            Toast.makeText(this,
                    Integer.toString(data.getIntExtra("age3",0)),
                    Toast.LENGTH_LONG).show();
            // get result using getData
            Toast.makeText(this,
                    data.getData().toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
