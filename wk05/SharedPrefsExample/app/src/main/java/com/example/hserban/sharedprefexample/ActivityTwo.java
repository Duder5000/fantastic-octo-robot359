package com.example.hserban.sharedprefexample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityTwo extends Activity implements View.OnClickListener{
    private Button buttonEnterInfo;
    private TextView displayInfo;

    public static final String DEFAULT = "not available";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        buttonEnterInfo = (Button)findViewById(R.id.button);
        buttonEnterInfo.setOnClickListener(this);

        displayInfo = (TextView)findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v) {

        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String firstName = sharedPrefs.getString("firstName", DEFAULT);
        String lastName = sharedPrefs.getString("lastName", DEFAULT);
        String colorSelected = sharedPrefs.getString("selectedColor", DEFAULT);

        if ( firstName.equals(DEFAULT) || lastName.equals(DEFAULT) || colorSelected.equals(DEFAULT))
        {
            Toast.makeText(this, "No data found. Please enter names and select color.", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Data retrieve success", Toast.LENGTH_LONG).show();
            displayInfo.setText("Welcome " + firstName + " " + lastName);
            displayInfo.setBackgroundColor((Color.parseColor(colorSelected)));
        }
    }
}

