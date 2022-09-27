package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class WordEntry extends Activity implements View.OnClickListener {

    public static final String RESULT_KEY = "WORD";
    EditText wordEntryEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_entry);
    }

    @Override
    public void onClick(View v){
        String word = wordEntryEditText.getText().toString();
        Toast.makeText(this, word, Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        i.putExtra("RESULT_KEY", word);

        if(word.equals("")){
            setResult(RESULT_CANCELED,i);
        }else{
            setResult(RESULT_OK,i);
        }

        finish();
    }
}
