package cvora.chapter7_content_providers;

import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UsingOwnContentProvider_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_own_content_provider_);
    }

    public void onClickAddTitle(View view){

        ContentValues values = new ContentValues();
        values.put(BookContentProvider.TITLE,((EditText)findViewById(R.id.txtTitle)).getText().toString());
        values.put(BookContentProvider.ISBN,((EditText)findViewById(R.id.txtISBN)).getText().toString());
        Uri uri = getContentResolver().insert(BookContentProvider.CONTENT_URI,values);
        Toast.makeText(getBaseContext(),uri.toString(),Toast.LENGTH_LONG).show();

    }

    public void onClickRetrieveTitles(View view){


        Uri allTitles = Uri.parse("content://cvora.chapter7_content_providers.books/books");
        Cursor c;
        if(Build.VERSION.SDK_INT < 11){
            c = managedQuery(allTitles,null,null,null,"title desc");
        }else{
            CursorLoader cursorLoader = new CursorLoader(this,allTitles,null,null,null,"title desc");
            c = cursorLoader.loadInBackground();
        }
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(
                                BookContentProvider._ID)) + ", " +
                                c.getString(c.getColumnIndex(
                                        BookContentProvider.TITLE)) + ", " +
                                c.getString(c.getColumnIndex(
                                        BookContentProvider.ISBN)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

    public void updateTitle() {
        ContentValues editedValues = new ContentValues();
        editedValues.put(BookContentProvider.TITLE, "Android Tips and Tricks");
        getContentResolver().update(
                Uri.parse(
                        "content://cvora.chapter7_content_providers.books/books/2"),
                editedValues,
                null,
                null);
    }

    public void deleteTitle() {

        //---delete a title---
        getContentResolver().delete(
                Uri.parse("content://cvora.chapter7_content_providers.books/books/2"),
                null, null);


        //---delete all titles---
        getContentResolver().delete(
                Uri.parse("content://net.learn2develop.provider.Books/books"),
                null, null);

    }


}
