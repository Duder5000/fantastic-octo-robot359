package com.example.helmine.recyclerviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText plantName, plantType, selectType, plantLatin, plantLoc;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plantName = (EditText)findViewById(R.id.plantNameEditText);
        plantType = (EditText)findViewById(R.id.plantTypeEditText);
        selectType = (EditText)findViewById(R.id.selectTypeEditText);
        plantLatin = (EditText)findViewById(R.id.latinEditText);
        plantLoc = (EditText)findViewById(R.id.locEditText);

        db = new MyDatabase(this);
    }


    public void addPlant (View view)
    {
        String name = plantName.getText().toString();
        String type = plantType.getText().toString();
        Toast.makeText(this, name + type, Toast.LENGTH_SHORT).show();
        long id = db.insertData(name, type);
        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        plantName.setText("");
        plantType.setText("");

    }

    public void viewResults(View view)
    {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }

    public void viewQueryResults (View view)
    {
        String userInputType = selectType.getText().toString();
        String queryResults = db.getSelectedData(userInputType);
        Toast.makeText(this, queryResults, Toast.LENGTH_LONG).show();
    }

    public void delete(View view) {
        int count = db.deleteRow();
        Toast.makeText(this, ""+ count, Toast.LENGTH_SHORT).show();
    }
}



