package com.example.hserban.sharedprefexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private RadioGroup colorButton, radioGroupColorsText, textSize;
    String firstName;
    String lastName;
    String color, colorText;
    Float textType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("alex", "~~~Start App~~~");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);

        goButton= (Button)findViewById(R.id.goButton);
        saveButton= (Button)findViewById(R.id.saveButton);

        firstNameEditText = (EditText)findViewById(R.id.firstnameEditText);
        lastNameEditText = (EditText)findViewById(R.id.lastnameEditText);

        colorButton = (RadioGroup) findViewById(R.id.radioGroupColors);
        colorButton.setOnCheckedChangeListener(this);

        radioGroupColorsText = (RadioGroup) findViewById(R.id.radioGroupColorsText);
        radioGroupColorsText.setOnCheckedChangeListener(this);

        textSize = (RadioGroup) findViewById(R.id.textSize);
        textSize.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //set color according to selected RadioButton
        Log.d("alex", "group=" + group + ", checkedId=" + checkedId);

//        if(group == "textSize"){
//            Log.d("alex","worked");
//        }else{
//            Log.d("alex","didnt worked");
//        }

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
            case R.id.radioRedText: //Text Color Section
                colorText = "#ff0000";
                break;
            case R.id.radioBlueText:
                colorText = "#0000ff";
                break;
            case R.id.radioGreenText:
                colorText = "#00ff00";
                break;
            case R.id.radioBrownText:
                colorText = "#a52a2a";
                break;
            case R.id.radioYellowText:
                colorText = "#ffff00";
                break;
            case R.id.textS: //Text Size Section
                textType = Float.valueOf(20);
                break;
            case R.id.textM:
                textType = Float.valueOf(30);
                break;
            case R.id.textL:
                textType = Float.valueOf(40);
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

        editor.putString("selectedTextColor", colorText);
        editor.putFloat("selectedTextSize", textType);

        Toast.makeText(this, "First, last names and color saved to Preferences", Toast.LENGTH_LONG).show();
        editor.commit();
    }

    public void gotoTwo(View view) {
        Intent i = new Intent (this, ActivityTwo.class);
        startActivity(i);
    }
}
