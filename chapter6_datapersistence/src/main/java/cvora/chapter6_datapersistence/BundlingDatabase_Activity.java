package cvora.chapter6_datapersistence;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BundlingDatabase_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundling_database_);

        DBAdapter db = new DBAdapter(this);
        try{
            String destPath = "/data/data/" + getPackageName() + "/databases";
            File f = new File(destPath);
            if(!f.exists()){
                f.mkdirs();
                f.createNewFile();
            }
            CopyDB(getBaseContext().getAssets().open("mydatabase1.db"),new FileOutputStream(destPath + "/MyTestDB"));

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        db.open();
        Cursor c = db.getAllContacts();
        if(c.moveToFirst()){
            do{
                DisplayContact(c);
            }while(c.moveToNext());
        }
        db.close();
    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException{
        byte [] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    }

    public void DisplayContact(Cursor cursor){
        Toast.makeText(this,
                "id: "+cursor.getString(0) + "\n" +
                        "Name: "+cursor.getString(1) + "\n" +
                        "Email: "+cursor.getString(2),
                Toast.LENGTH_LONG).show();
    }
}
