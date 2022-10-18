package com.example.hserban.plantdatabasenew;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText plantName, plantType, selectType;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plantName = (EditText)findViewById(R.id.plantNameEditText);
        plantType = (EditText)findViewById(R.id.plantTypeEditText);
        selectType = (EditText)findViewById(R.id.selectTypeEditText);

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
        String data = db.getData();
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    public void viewQueryResults (View view)
    {
        String userInputType = selectType.getText().toString();
        String queryResults = db.getSelectedData(userInputType);
        Toast.makeText(this, queryResults, Toast.LENGTH_LONG).show();
    }


}
