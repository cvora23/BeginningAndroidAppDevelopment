package cvora.chapter7_content_providers;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContactsContentProvider extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_content_provider);

        //Uri allContacts = Uri.parse("content://contacts/people");
        Uri allContacts = ContactsContract.Contacts.CONTENT_URI;
        Cursor c;
        if(Build.VERSION.SDK_INT < 11){
            c = managedQuery(allContacts,null,null,null,null);
        }else{
            CursorLoader cursorLoader = new CursorLoader(this,allContacts,null,null,null,null);
            c = cursorLoader.loadInBackground();
        }

        String [] columns = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts._ID };

        int [] views = new int[] {R.id.contactName,R.id.contactID};

        SimpleCursorAdapter adapter;

        if(Build.VERSION.SDK_INT < 11){
            adapter = new SimpleCursorAdapter(this,R.layout.activity_contacts_content_provider,c,columns,views);
        }else{
            adapter = new SimpleCursorAdapter(this,R.layout.activity_contacts_content_provider,c,columns,views, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }

        this.setListAdapter(adapter);
        printContacts(c);
    }

    private void printContacts(Cursor c){

        if(c.moveToFirst()){
            do{
                String contactID = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                String contactDisplayName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.v("Content Providers",contactID + "," + contactDisplayName);

                // -- get phone numnber --
                int hasPhone = c.getInt(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if(hasPhone == 1){
                    Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
                    contactID,null,null);

                    while(phoneCursor.moveToNext()){
                        Log.v("Content Providers",phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    }
                    phoneCursor.close();

                }
            }while (c.moveToNext());
        }

    }
}
