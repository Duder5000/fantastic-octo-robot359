package com.example.helmine.recyclerviewdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by helmine on 2015-02-04. updated by hanieh in 2022.
 */
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
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, name);
        contentValues.put(Constants.TYPE, type);
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.NAME, Constants.TYPE};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
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

    public int deleteRow(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {"herb"};
        int count = db.delete(Constants.TABLE_NAME, Constants.TYPE + "=?", whereArgs);
        return count;
    }


}
