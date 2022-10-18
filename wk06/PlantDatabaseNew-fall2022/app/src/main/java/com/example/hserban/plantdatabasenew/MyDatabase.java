package com.example.hserban.plantdatabasenew;

/**
 * Created by hserban on 2/20/18. Updated by hshakeri in 2022.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    public MyDatabase (Context c){
        context = c;
        helper = new MyHelper(context);
    }

    public long insertData (String name, String type)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, name);
        contentValues.put(Constants.TYPE, type);
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.UID, Constants.NAME, Constants.TYPE};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);

        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int index = cursor.getInt(0);
            String name = cursor.getString(1);
            String type = cursor.getString(2);
            buffer.append(index + " " + name + " " + type + "\n");
        }
        return buffer.toString();
    }

    public String getSelectedData(String type)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.NAME, Constants.TYPE};

        String selection = Constants.TYPE + "='" +type+ "'";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(Constants.NAME);
            int index2 = cursor.getColumnIndex(Constants.TYPE);
            String plantName = cursor.getString(index1);
            String plantType = cursor.getString(index2);
            buffer.append(plantName + " " + plantType + "\n");
        }
        return buffer.toString();
    }

}
