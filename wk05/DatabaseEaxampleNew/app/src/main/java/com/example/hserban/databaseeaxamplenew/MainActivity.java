package com.example.hserban.databaseeaxamplenew;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class MainActivity extends Activity {

    HelperClass helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new HelperClass(this);

        SQLiteDatabase myDatabase = helper.getWritableDatabase();
    }

}
