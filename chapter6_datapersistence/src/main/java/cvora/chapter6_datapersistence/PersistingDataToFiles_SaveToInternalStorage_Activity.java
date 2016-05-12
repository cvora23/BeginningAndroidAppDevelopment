package cvora.chapter6_datapersistence;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PersistingDataToFiles_SaveToInternalStorage_Activity extends AppCompatActivity {

    EditText textBox,textBox2;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persisting_data_to_files__save_to_internal_storage_);

        textBox = (EditText)findViewById(R.id.txtText1);
        textBox2 = (EditText)findViewById(R.id.txtText2);
    }

    public void onClickSave(View view){
        String str = textBox.getText().toString();
        try{
            FileOutputStream fOut = openFileOutput("textfile.txt",MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(str);
            osw.flush();
            osw.close();

            Toast.makeText(getBaseContext(),"File Saved Successfully!",Toast.LENGTH_SHORT).show();
            textBox.setText("");

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void onClickLoad(View view){

        try{
            FileInputStream fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while((charRead = isr.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            textBox.setText(s);
            Toast.makeText(getBaseContext(),"File Loaded Successfully !",Toast.LENGTH_SHORT).show();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    public void onClickExternalSave(View view){

        String str = textBox2.getText().toString();
        try{
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(sdCard.getAbsolutePath());
            File file = new File(directory,"textFile.txt");
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(str);
            osw.flush();
            osw.close();

            Toast.makeText(getBaseContext(),"File Saved Successfully!",Toast.LENGTH_SHORT).show();
            textBox2.setText("");
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    public void onClickExternalLoad(View view){
        try{
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(sdCard.getAbsolutePath());
            File file = new File(directory,"textfile.txt");
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while((charRead = isr.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            textBox2.setText(s);
            Toast.makeText(getBaseContext(),"File Loaded Successfully !",Toast.LENGTH_SHORT).show();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }


}
