package com.example.lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_WORD_ENTRY = 0;

    Button enterWordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterWordButton = findViewById(R.id.enterWordButton);
        enterWordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i = new Intent(this, WordEntry.class);
        startActivityForResult(i,REQUEST_WORD_ENTRY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String userWord;
        Uri webpage;

        if(requestCode == REQUEST_WORD_ENTRY){
            if(resultCode == RESULT_OK){
                String resultCodeStr = Integer.toString(resultCode);
                Toast.makeText(this, resultCodeStr, Toast.LENGTH_SHORT).show();
                if(data.hasExtra(WordEntry.RESULT_KEY)){
                    userWord = data.getExtras().getString("WORD");
                    webpage = Uri.parse("http://www.merriam-webster.com/dictionary/" + userWord);
                    Intent i = new Intent(Intent.ACTION_VIEW,webpage);
                    startActivity(i);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}