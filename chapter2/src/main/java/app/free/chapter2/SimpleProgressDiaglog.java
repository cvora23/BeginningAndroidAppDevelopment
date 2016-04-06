package app.free.chapter2;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SimpleProgressDiaglog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_progress_diaglog);
    }

    public void onClick(View v){
        showDialog();
    }

    void showDialog() {
        DialogFragment newFragment = MyAlertDialogFragment.newInstance(
                R.string.alert_dialog_two_buttons_title);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public void doPositiveClick() {
        // Do stuff here.
        Log.i("Dialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.
        Log.i("Dialog", "Negative click!");
    }


    public void onClick2(View v){
        // show the progress dialog
        final ProgressDialog dialog = ProgressDialog.show(
                this,"Doing something","Please Wait ....",true);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    Thread.sleep(5000);
                    dialog.dismiss();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
