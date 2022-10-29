package iat359course.ca.recyclerviewnew;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener, View.OnClickListener{
    RecyclerView myRecycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Sensor> sensorArr;

    SensorManager mySensorManager;
    List<Sensor> deviceSensor;

    Sensor gyro, light, acc;
    ToneGenerator toneG;
    Date flatCounterStart;
    boolean isFlat = false;
    boolean timerRunning = false;

    float gyroVal0;
    float gyroVal1;
    float gyroVal2;
    boolean checkFlatVal = false;
    boolean doChecks = false;

    private final int interval = 5000; // 5 Second
    private Handler handler = new Handler();

    boolean stationaryButtonPressed = false;

    private Button buttonStationary;
    boolean buttonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doChecks = true;
//        stationaryButtonPressed = true;

        toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 75);
        flatCounterStart = Calendar.getInstance().getTime(); //remove later

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        deviceSensor = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorArr = new ArrayList<Sensor>();

        for (Sensor j: deviceSensor){
            sensorArr.add(j);
        }

        gyro = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        light = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        acc = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        myRecycler = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(mLayoutManager);

        adapter = new MyAdapter(sensorArr, getApplicationContext(), deviceSensor);
        myRecycler.setAdapter(adapter);

        buttonStationary = (Button)findViewById(R.id.buttonStationary);
        buttonStationary.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
        mySensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        mySensorManager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){

        //Part d)
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
//            Log.d("SensorTest", "Gyro Sensor 0 = " + event.values[0]);
//            Log.d("SensorTest", "Gyro Sensor 1 = " + event.values[1]);
//            Log.d("SensorTest", "Gyro Sensor 2 = " + event.values[2]);
            if(doChecks){
                gyroVal0 = event.values[0];
                gyroVal1 = event.values[1];
                gyroVal2 = event.values[2];

                if (gyroVal0 == 0 && gyroVal1 == 0 && gyroVal2 == 0) {
                    isFlat = true;
                } else {
                    isFlat = false;
                }

                if (isFlat && timerRunning) {
                    //check if timer is at 5 seconds
                    if (checkFlatVal) {
                        Log.d("SensorTest", "Phone flat for 5 seconds");
                    }
                } else if (isFlat && !timerRunning) {
                    timerRunning = true;
                    //start timer
                    Log.d("SensorTest", "Timer start");
                    handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                    handler.postDelayed(runnable, interval);
                } else if (!isFlat && timerRunning) {
                    timerRunning = false;
                    //clear timer
                }
            }

        }

        //Part c)
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            if(event.values[0] == 0){
                Log.d("SensorTest", "Light Sensor = " + event.values[0]);
                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
            }
        }

        //Part e)
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//            Log.d("SensorTest", "Acc Sensor 0 = " + event.values[0]);
//            Log.d("SensorTest", "Acc Sensor 1 = " + event.values[1]);
//            Log.d("SensorTest", "Acc Sensor 2 = " + event.values[2]);

            float accVal0 = event.values[0];
            float accVal1 = event.values[1];
            float accVal2 = event.values[2];
            if (accVal0 == 0 && accVal2 == 0 && buttonClicked) {
                Toast toast = Toast.makeText(this, "Phone is stationary", Toast.LENGTH_SHORT);
                toast.show();
                //Do some stuff
            }
            buttonClicked = false;
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


//    https://stackoverflow.com/questions/1877417/how-to-set-a-timer-in-android
    private Runnable runnable = new Runnable(){
        public void run() {
            Log.d("SensorTest", "Timer end");
            checkFlatVal = checkFlat();
            timerRunning = false;
            doChecks = false;
        }
    };

    boolean checkFlat(){
        if(gyroVal0 == 0 && gyroVal1 == 0 && gyroVal2 == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("SensorTest", "Button Clicked");
        buttonClicked = true;
    }

}
