package com.example.hserban.lecture8_samplecode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void webView(View view) {
        Intent intent = new Intent (this, WebViewActivity.class);
        startActivity(intent);
    }

    public void networkOperations(View view) {
        Intent intent = new Intent (this, NetworkOperationsActivity.class);
        startActivity(intent);
    }

    public void currentWeather(View view) {
        Intent intent = new Intent (this, CurrentWeatherActivity.class);
        startActivity(intent);
    }
}
