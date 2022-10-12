package com.example.hserban.explicitintentdemonew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lab4 extends AppCompatActivity implements View.OnClickListener {

    private Button button2;
    private EditText editTextTextPersonName;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);

        Log.e("activityTest","Start of Lab4");

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        editTextTextPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
    }

    @Override
    public void onClick(View v){
        Log.e("activityTest","In onClick");
        word = editTextTextPersonName.getText().toString();

        Toast.makeText(this, word, Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        i.putExtra("WORD", word);

        setResult(RESULT_OK, i);
        Log.e("activityTest","Past setResult");
        finish();
    }



}