package com.example.hserban.explicitintentdemonew;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ActivityOne extends Activity implements View.OnClickListener{
    private Button buttonEnterInfo;
    private TextView displayInfo;
    static final int REQUEST_CODE = 0; //this is the REQUEST_CODE for requesting result from ActivityTwo activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);
        buttonEnterInfo = (Button)findViewById(R.id.button);
        buttonEnterInfo.setOnClickListener(this);

        displayInfo = (TextView)findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, ActivityTwo.class);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE) //check that we're processing the response from WordEntry
        {
            if(resultCode==RESULT_OK) //make sure the request was successful
            {
                if ((data.hasExtra("First Name"))&&(data.hasExtra("Last Name"))&&(data.hasExtra("Selected Color")));
                {
                    String firstname_entered = data.getExtras().getString("First Name");
                    String lastname_entered = data.getExtras().getString("Last Name");
                    String color_selected = data.getExtras().getString("Selected Color");
                    displayInfo.setText("Welcome " + firstname_entered + " " +lastname_entered);
                    displayInfo.setBackgroundColor((Color.parseColor(color_selected)));
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

