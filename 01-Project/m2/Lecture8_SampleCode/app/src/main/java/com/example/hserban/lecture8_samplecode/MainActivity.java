package com.example.hserban.lecture8_samplecode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
    }

    public void goToNewCampaign(View view) {
        Intent intent = new Intent (this, WebViewActivity.class);
        Toast.makeText(context, "goToNewCampaigns", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

    public void goToOldCampaigns(View view) {
        Intent intent = new Intent (this, NetworkOperationsActivity.class);
        Toast.makeText(context, "goToOldCampaigns", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }

    public void goToRoller(View view) {
        Intent intent = new Intent (this, CurrentWeatherActivity.class);
        Toast.makeText(context, "goToRoller", Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
}
