package com.example.hserban.databaseeaxamplenew;

/**
 * Created by hserban on 2/6/18.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by helmine on 2015-01-26.
 */
public class HelperClass extends SQLiteOpenHelper{
    private Context context;
    private static final String DATABASE_NAME = "plantdatabase";
    private static final String TABLE_NAME = "PLANTSTABLE";
    private static final String UID = "_id";
    private static final String NAME = "Name";
    private static final String TYPE = "Type";
    private static final int DATABASE_VERSION = 2;

    private static final String CREATE_TABLE =
            " CREATE TABLE " +TABLE_NAME+ " (" +UID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +NAME+ " VARCHAR(255), " +TYPE+ " VARCHAR(255));";
           // " CREATE TABLE " +TABLE_NAME+ " (" +UID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +NAME+ " VARCHAR(255)) ;";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " +TABLE_NAME;

    public HelperClass (Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "exception onCreate() db", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "onUpgrade called", Toast.LENGTH_LONG).show();
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            Toast.makeText(context, "exception onUpgrade() db", Toast.LENGTH_LONG).show();
        }
    }
}
