package cvora.chapter6_datapersistence;

import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DatabasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_databases);

        DBAdapter db = new DBAdapter(this);

        db.open();
        long id = db.insertContact("Chintan2","cv2ewe2@gmail.com");
        Log.d("DB","id = "+id);

        // get all contacts
        Cursor cursor = db.getAllContacts();
        if(cursor.moveToFirst()){
            do{
                DisplayContact(cursor);
            }while (cursor.moveToNext());
        }

        // get a contact
        cursor = db.getContact(2);
        if(cursor.moveToFirst())
            DisplayContact(cursor);
        else
            Toast.makeText(this,"No contact found",Toast.LENGTH_LONG).show();

        // -updating contact
        if(db.updateContact(1,"Chintan Vora","cv22@gmail.com"))
            Toast.makeText(this,"Update Success",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Update Failed",Toast.LENGTH_LONG).show();

        // -delete a contact
        if(db.deleteContact(1))
            Toast.makeText(this,"Delete success",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Delete Failed.",Toast.LENGTH_LONG).show();

        db.close();

    }

    public void DisplayContact(Cursor cursor){
        Toast.makeText(this,
                "id: "+cursor.getString(0) + "\n" +
                "Name: "+cursor.getString(1) + "\n" +
                "Email: "+cursor.getString(2),
                Toast.LENGTH_LONG).show();
    }
}
