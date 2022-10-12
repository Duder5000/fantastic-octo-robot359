package com.example.hserban.sharedprefexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ActivityOne extends Activity implements RadioGroup.OnCheckedChangeListener{

    private Button goButton, saveButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RadioGroup colorButton;
    String firstName;
    String lastName;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);

        goButton= (Button)findViewById(R.id.goButton);
        saveButton= (Button)findViewById(R.id.saveButton);

        firstNameEditText = (EditText)findViewById(R.id.firstnameEditText);
        lastNameEditText = (EditText)findViewById(R.id.lastnameEditText);
        colorButton = (RadioGroup) findViewById(R.id.radioGroupColors);
        colorButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //set color according to selected RadioButton
        switch (checkedId) {
            case R.id.radioRed:
                color = "#ff0000";
                break;
            case R.id.radioBlue:
                color = "#0000ff";
                break;
            case R.id.radioGreen:
                color = "#00ff00";
                break;
            case R.id.radioBrown:
                color = "#a52a2a";
                break;
            case R.id.radioYellow:
                color = "#ffff00";
                break;
        }
    }

    public void saveData(View view) {
        firstName =  firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();

        Toast.makeText(this, firstName + lastName + color, Toast.LENGTH_SHORT).show();

        Intent i = new Intent (this, ActivityTwo.class);

        SharedPreferences sharedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.putString("selectedColor", color);
        Toast.makeText(this, "First, last names and color saved to Preferences", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void gotoTwo(View view) {
        Intent i = new Intent (this, ActivityTwo.class);
        startActivity(i);
    }
}
