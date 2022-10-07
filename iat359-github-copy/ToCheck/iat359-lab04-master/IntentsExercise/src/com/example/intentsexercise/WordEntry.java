package com.example.intentsexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WordEntry extends Activity implements OnClickListener{
	
	Button lookupButton; 
	EditText wordEntryEditText; 
	String word; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_entry);
		
		lookupButton = (Button) findViewById(R.id.lookupButton);
		lookupButton.setOnClickListener(this);
		
		wordEntryEditText = (EditText)findViewById(R.id.wordEditText);
	
	}

	@Override
	public void onClick(View v) {
		word =  wordEntryEditText.getText().toString();
		Toast.makeText(this, word,Toast.LENGTH_SHORT).show();
		
		
		Intent i = new Intent(); //getting the intent that has started this activity
		i.putExtra("WORD", word);
		setResult(RESULT_OK, i);
		finish();
		
	}

}
