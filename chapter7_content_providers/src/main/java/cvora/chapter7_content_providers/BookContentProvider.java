package cvora.chapter7_content_providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Admin on 5/13/2016.
 */
public class BookContentProvider extends ContentProvider{

    static final String PROVIDER_NAME = "cvora.chapter7_content_providers.books";
    static final Uri CONTENT_URI = Uri.parse("content://"+PROVIDER_NAME+"/books");

    static final String _ID = "_id";
    static final String TITLE = "title";
    static final String ISBN = "isbn";

    static final int BOOKS = 1;
    static final int BOOK_ID = 2;

    private static final UriMatcher urimatcher;
    static {
        urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urimatcher.addURI(PROVIDER_NAME,"books",BOOKS);
        urimatcher.addURI(PROVIDER_NAME,"books/#",BOOK_ID);
    }

    SQLiteDatabase booksDB;
    static final String DATABASE_NAME = "Books";
    static final String DATABASE_TABLE = "titles";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE +
                    " (_id integer primary key autoincrement, "
                    + "title text not null, isbn text not null);";

    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Content Provider DB","Upgrading database from version "+oldVersion + " to "+newVersion +
            ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        booksDB = databaseHelper.getWritableDatabase();
        return (booksDB == null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(DATABASE_TABLE);

        if(urimatcher.match(uri) == BOOK_ID){
            sqLiteQueryBuilder.appendWhere(_ID + "="+uri.getPathSegments().get(1));
        }

        if(sortOrder == null || sortOrder == ""){
            sortOrder = TITLE;
        }

        Cursor cursor = sqLiteQueryBuilder.query(booksDB,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (urimatcher.match(uri)){
            case BOOKS:{
                return "vnd.android.cursor.dir/vnd.learn2develop.books ";
            }
            case BOOK_ID:{
                return "vnd.android.cursor.item/vnd.learn2develop.books ";
            }
            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowID = booksDB.insert(DATABASE_TABLE,"",values);

        if(rowID > 0){
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(uri1,null);
            return uri1;
        }
        throw new SQLException("Failed to insert row into " + uri);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count = 0;
        switch (urimatcher.match(uri)){
            case BOOKS:
                count = booksDB.delete(DATABASE_TABLE,selection,selectionArgs);
                break;
            case BOOK_ID:
                String id = uri.getPathSegments().get(1);
                count = booksDB.delete(
                        DATABASE_TABLE,
                        _ID + " = " + id +
                                (!TextUtils.isEmpty(selection) ? " AND (" +
                                        selection + ')' : ""),
                        selectionArgs);
                break;
            default: throw new IllegalArgumentException("Unknow uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (urimatcher.match(uri)){
            case BOOKS:
                count = booksDB.update(
                        DATABASE_TABLE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case BOOK_ID:
                count = booksDB.update(
                        DATABASE_TABLE,
                        values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +
                                        selection + ')' : ""),
                        selectionArgs);
                break;
            default: throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
