package cvora.chapter11_developing_android_services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MySimpleService extends Service {

    int counter = 0;
    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();

    public MySimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();

        doSomethingRepeatedly();

        try{
//            int result = DownloadFile(new URL("http://www.amazon.com/somefile.pdf"));
//            Toast.makeText(getBaseContext(),"Downloaded "+result + " bytes",Toast.LENGTH_LONG).show();

            new DoBackgroundTask().execute(
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf")
                    );

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return START_STICKY;
    }

    private void doSomethingRepeatedly(){
        Log.d("MySimpleService","doSomethingRepeatedly");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("----MySimpleService----",String.valueOf(++counter));
            }
        },0,UPDATE_INTERVAL);
    }

    private int DownloadFile(URL url){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 100;
    }

    private class DoBackgroundTask extends AsyncTask<URL,Integer,Long>{

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for(int i=0;i<count;i++){
                totalBytesDownloaded += DownloadFile(urls[i]);
                publishProgress((int) (((i+1)/(float)count)*100));
            }
            return totalBytesDownloaded;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d("Downloading files",String.valueOf(values[0]) + "% downloaded");
            Toast.makeText(getBaseContext(),String.valueOf(values[0]) + " % downloaded",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(),"Downloaded " + result + " bytes",Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(timer != null){
            timer.cancel();
        }

        Toast.makeText(this,"Service Now Destroyed",Toast.LENGTH_SHORT).show();
    }
}
