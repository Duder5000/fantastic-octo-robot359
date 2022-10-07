package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.Nullable;

public class WordEntry extends Activity implements TextWatcher, View.OnClickListener {

    public static final String RESULT_KEY = "WORD";
    EditText wordEntryEditText;
    Button lookUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_entry);

        wordEntryEditText = (EditText) findViewById(R.id.wordEntryEditText);
        wordEntryEditText.addTextChangedListener(this);

        lookUpButton = findViewById(R.id.lookUpButton);
        lookUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        String word = wordEntryEditText.getText().toString();
        Intent i = new Intent();
        i.putExtra(RESULT_KEY, word);

        if(word.equals("")){
            setResult(RESULT_CANCELED,i);
        }else{
            setResult(RESULT_OK,i);
        }

        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
