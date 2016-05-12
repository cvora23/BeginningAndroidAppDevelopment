package cvora.chapter6_datapersistence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UseStaticResource_Activity extends AppCompatActivity {

    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_static_resource_);

        InputStream is = this.getResources().openRawResource(R.raw.textfile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;
        try{
            while ((str = br.readLine()) != null){
                Toast.makeText(getBaseContext(),str,Toast.LENGTH_SHORT).show();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
