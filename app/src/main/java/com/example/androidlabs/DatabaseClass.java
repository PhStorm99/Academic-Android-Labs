package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.Arrays;

public class DatabaseClass extends SQLiteOpenHelper {

    private static final int VERSION = 3;
    private static final String DB_NAME = "MessagesDB";
    private static final String DB_TABLE = "Messages_Table";
    private static final String COL_MESSAGE = "Message";
    private static final String COL_ISSEND = "IsSend";
    private static final String COL_MESSAGEID = "MessageID";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + COL_MESSAGEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MESSAGE + " TEXT, " + COL_ISSEND + " BIT);";

    public DatabaseClass(Context context) {

        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(db);

    }

    public boolean insertData(String message, boolean isSend) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_MESSAGE, message);

        if (isSend)

            contentValues.put(COL_ISSEND, 0);

        else

            contentValues.put(COL_ISSEND, 1);

        long result = db.insert(DB_TABLE, null, contentValues);


        return result != -1;

    }
    public Cursor viewData() {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + DB_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        Log.e("Database Version Number", Integer.toString(db.getVersion()));

        Log.e("Column Count", Integer.toString(cursor.getColumnCount()));

        Log.e("Column Names", Arrays.toString(cursor.getColumnNames()));

        Log.e("Row Count", Integer.toString(cursor.getCount()));

        Log.e("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));

        return cursor;
    }
}
