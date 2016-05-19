package cvora.chapter11_developing_android_services;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class SimpleServiceActivity extends AppCompatActivity {

    IntentFilter intentFilter;
    MySimpleServiceWithBinder mySimpleServiceWithBinder;
    Intent intent;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mySimpleServiceWithBinder = ((MySimpleServiceWithBinder.MyBinder)service).getService();
            try{
                URL[] urls = new URL[]{
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf")
                };
                mySimpleServiceWithBinder.urls = urls;
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            startService(intent);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mySimpleServiceWithBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_service_activiyt);
    }

    @Override
    protected void onResume() {
        super.onResume();

        intentFilter = new IntentFilter();
        intentFilter.addAction("FILE_DOWNLOADED_ACTION");

        registerReceiver(intentReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(intentReceiver);
    }

    public void startService(View view){
        startService(new Intent(getBaseContext(),MySimpleService.class));
    }

    public void stopService(View view){
        stopService(new Intent(getBaseContext(),MySimpleService.class));
    }

    public void startIntentService(View view){
        Log.d("SimpleServiceActivity","Starting Intent Service");
        startService(new Intent(getBaseContext(),MyIntentService.class));
    }

    public void startIntentServiceWithBinder(View view){
        intent = new Intent(getBaseContext(),MySimpleServiceWithBinder.class);
        bindService(intent,connection,Context.BIND_AUTO_CREATE);
    }

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getBaseContext(),"File downloaded !",Toast.LENGTH_LONG).show();
        }
    };
}
