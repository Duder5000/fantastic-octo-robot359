package com.example.intentsexercise;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	static final int REQUEST_WORD_ENTRY = 0; //this is the REQUEST_CODE for requesting result from WordEntry activity
	
	Button enterWordButton; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		enterWordButton = (Button) findViewById(R.id.enterWordButton);
		enterWordButton.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(MainActivity.this, WordEntry.class);
		startActivityForResult(i, REQUEST_WORD_ENTRY);  
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==REQUEST_WORD_ENTRY) //check that we're processing the response from WordEntry
		{
			if(resultCode==RESULT_OK) //make sure the request was successful
			{
				if (data.hasExtra("WORD"))
				{			
					String word_entered = data.getExtras().getString("WORD");
					Uri webpage = Uri.parse("http://www.merriam-webster.com/dictionary/"+ word_entered);
					Intent webIntent = new Intent(Intent.ACTION_VIEW,webpage);	
					startActivity(webIntent);
				}
			}
		}
			
		super.onActivityResult(requestCode, resultCode, data);
	}


	

}
