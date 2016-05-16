package cvora.chapter8_messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 5/14/2016.
 */
public class SMS_Receiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "SMS from ";
        if(bundle != null){
            Object[] pdus = (Object[])bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0;i<msgs.length;i++){
                msgs[i] = SmsMessage.createFromPdu((byte [])pdus[i]);
                if (i==0){
                    str += msgs[i].getOriginatingAddress();
                    str += " : ";
                }
                str += msgs[i].getMessageBody().toString();
            }
            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
            Log.d("SMS_Receiver",str);


            // -send a broadcast Intent to update the sms received in the activity.
            Intent broradcastIntent = new Intent();
            broradcastIntent.setAction("SMS_RECEIVED_ACTION");
            broradcastIntent.putExtra("sms",str);
            context.sendBroadcast(broradcastIntent);
        }

    }
}
