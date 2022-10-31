package com.example.hserban.explicitintentdemonew;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityOne extends Activity implements View.OnClickListener{
    private Button buttonEnterInfo;
    private TextView displayInfo;
    static final int REQUEST_CODE = 0; //this is the REQUEST_CODE for requesting result from ActivityTwo activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_one);

        Log.e("activityTest","~~~App Start~~~");

        buttonEnterInfo = (Button)findViewById(R.id.button);
        buttonEnterInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Intent i = new Intent(this, ActivityTwo.class);
//        startActivityForResult(i, REQUEST_CODE);
        Intent j = new Intent(this, Lab4.class);
        startActivityForResult(j, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.e("activityTest","Back to ActivityOne, in onActivityResult");
        Log.e("activityTest", "requestCode=" + requestCode + ", RESULT_OK=" + resultCode);

//        String word_entered = "apple";
//        Uri webpage = Uri.parse("https://www.merriam-webster.com/" + word_entered);
//        Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
//        startActivity(webIntent);

        if (requestCode==REQUEST_CODE) //check that we're processing the response from WordEntry
        {
            if(resultCode==RESULT_OK) //make sure the request was successful
            {
                if(data.hasExtra("WORD")){
                    String word_entered = data.getExtras().getString("WORD");
                    Uri webpage = Uri.parse("https://www.merriam-webster.com/" + word_entered);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);
                    startActivity(webIntent);
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

