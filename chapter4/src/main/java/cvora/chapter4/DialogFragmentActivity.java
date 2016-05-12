package cvora.chapter4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DialogFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        DialogFragment1 dialogFragment1 = DialogFragment1.newInstance("Are you sure you want to do this ?");
        dialogFragment1.show(getSupportFragmentManager(),"dialog");

    }

    public void doPositiveClick(){
        Log.d("DialogFragmentActivity","User Clicks on OK");
    }

    public void doNegativeClick(){
        Log.d("DialogFragmentActivity","User Clicks on Cancel");
    }

}
