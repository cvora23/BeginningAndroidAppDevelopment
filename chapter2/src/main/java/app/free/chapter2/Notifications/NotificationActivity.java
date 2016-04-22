package app.free.chapter2.Notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.free.chapter2.R;

public class NotificationActivity extends Activity {

    int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);
    }

    public void onClick(View view){
        displayNotification();
    }

    protected void displayNotification(){


        // Pending Intent to launch activity if the user selects this notification
        Intent i = new Intent(this,NotificationView.class);
        i.putExtra("notificationID",notificationId);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,0);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Notification notif = new Notification.Builder(this)
                .setContentTitle("Reminder: Meeting starts in 5 mins")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText("System Alarm: Meeting with customer at 3pm....")
                .setContentIntent(pendingIntent).build();

        notif.vibrate = new long[] {100,250,100,500};
        nm.notify(notificationId,notif);;
    }
}
