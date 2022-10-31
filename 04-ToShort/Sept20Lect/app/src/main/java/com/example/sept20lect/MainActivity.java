package com.example.sept20lect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("cycleStage", "onCreate Called");
    }

//    @Override
    protected void onStart(Bundle savedInstanceState) {
        super.onStart();
        Log.d("cycleStage", "onStart Called");
    }

//    @Override
    protected void onResume(Bundle savedInstanceState) {
        super.onResume();
        Log.d("cycleStage", "onResume Called");
    }

//    @Override
    protected void onPause(Bundle savedInstanceState) {
        super.onPause();
        Log.d("cycleStage", "onPause Called");
    }

//    @Override
    protected void onStop(Bundle savedInstanceState) {
        super.onStop();
        Log.d("cycleStage", "onStop Called");
    }

//    @Override
    protected void onDestroy(Bundle savedInstanceState) {
        super.onDestroy();
        Log.d("cycleStage", "onDestroy Called");
    }

//    @Override
    protected void onRestart(Bundle savedInstanceState) {
        super.onRestart();
        Log.d("cycleStage", "onRestart Called");
    }

}