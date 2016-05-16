package cvora.chapter8_messaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class SendinSMS_Msg_Programmatically_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendin_sms__msg__programmatically_);
    }

    public void onClick(View view){
        sendSMS("5556","Hello my friends !");
    }

    private void sendSMS(String phoneNumber,String message){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,message,null,null);
    }

}
