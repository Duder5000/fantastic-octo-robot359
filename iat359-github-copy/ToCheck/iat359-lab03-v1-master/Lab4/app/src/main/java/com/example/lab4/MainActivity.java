package com.example.lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_WORD_ENTRY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                if(data.hasExtra(WordEntry.RESULT_KEY)){
                    userWord = data.getExtras().getString("WORD");
                    webpage = Uri.parse("http://www.merriam-webster.com/dictionary/" + userWord);
                    Intent i = new Intent(Intent.ACTION_VIEW,webpage);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}