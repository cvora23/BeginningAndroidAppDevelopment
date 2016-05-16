package cvora.chapter8_messaging;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SendingEmailsProgrammatically_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_emails_programmatically_);
    }

    public void onClick(View view){
        String[] to = {"cvora22@gmail.com"};
        String[] cc = {"chintanvora220587@gmail.com"};
        sendEmail(to,cc,"Hello","Hello my friends");
    }

    private void sendEmail(String[] emailAddresses,String[] carbonCopies,String subject,String message){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL,emailAddresses);
        emailIntent.putExtra(Intent.EXTRA_CC,carbonCopies);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));
    }
}
