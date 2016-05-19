package cvora.chapter11_developing_android_services;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThreadingActivity extends AppCompatActivity {

    static TextView textView;
    DoCountingTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading);
        textView = (TextView)findViewById(R.id.textView1);
    }

    public void startCounter(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){

                    final int valueOfi = i;

                    // update UI
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(valueOfi));
                        }
                    });
                    // insert a delay
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        Log.d("Threading",e.getLocalizedMessage());
                    }
                }
            }
        }).start();
    }

    static Handler UIupdater = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            byte [] buffer = (byte [])msg.obj;
            // convert byte array to string
            String strReceived = new String(buffer);
            // display the text received on the textview
            textView.setText(strReceived);
        }
    };

    public void startCounterUsingHandler(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<1000;i++){
                    ThreadingActivity.UIupdater.obtainMessage(0,String.valueOf(i).getBytes()).sendToTarget();
                    // insert a delay
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        Log.d("Threading",e.getLocalizedMessage());
                    }
                }
            }
        }).start();
    }

    public void startCounterUsingAsyncTask(View view){
        task = (DoCountingTask)new DoCountingTask().execute();
    }

    public void stopCounterUsingAsyncTask(View view){
        task.cancel(true);
    }

    private class DoCountingTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {

            for(int i=0;i<1000;i++){
                publishProgress(i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Log.d("Threading",e.getLocalizedMessage());
                }
                if(isCancelled())break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(values[0].toString());
            Log.d("Threading","Updating....");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopCounterUsingAsyncTask(textView);
    }
}
