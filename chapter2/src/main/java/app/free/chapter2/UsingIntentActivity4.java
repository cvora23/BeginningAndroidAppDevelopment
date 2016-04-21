package app.free.chapter2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UsingIntentActivity4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_intent4);
    }

    public void OnClick(View view){

        Intent data = new Intent();

        // get the EditText view
        EditText txt_username = (EditText)findViewById(R.id.txt_username);
        data.setData(Uri.parse(txt_username.getText().toString()));
        setResult(RESULT_OK, data);

        finish();

    }
}
