package app.free.chapter2;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SimpleProgressDialogActivity extends DialogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_progress_diaglog);
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

    public void onClick3(View v){

        // show the download progress dialog
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setTitle("Downloading file....");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "OK Clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel Clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
        dialog.setProgress(0);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=15;i++){
                    try{
                        Thread.sleep(1000);
                        // progress updating
                        dialog.incrementProgressBy((100/15));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }).start();

    }
}
