package com.example.hserban.explicitintentdemonew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ActivityTwo extends Activity implements OnClickListener, RadioGroup.OnCheckedChangeListener{

    private Button okButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RadioGroup colorButton;
    String firstName;
    String lastName;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        okButton = (Button)findViewById(R.id.buttonOK);
        okButton.setOnClickListener(this);
        firstNameEditText = (EditText)findViewById(R.id.firstnameEditText);
        lastNameEditText = (EditText)findViewById(R.id.lastnameEditText);
        colorButton = (RadioGroup) findViewById(R.id.radioGroupColors);
        colorButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        firstName =  firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        Toast.makeText(this, firstName + lastName + color, Toast.LENGTH_SHORT).show();

        Intent i = getIntent(); //getting the intent that has started this activity
        i.putExtra("First Name", firstName);
        i.putExtra("Last Name", lastName);
        i.putExtra("Selected Color", color);
        setResult(RESULT_OK, i);
        finish();
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
}
