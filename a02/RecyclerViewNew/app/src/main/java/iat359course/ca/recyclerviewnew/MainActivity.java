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
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
    RecyclerView myRecycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Sensor> sensorArr;

    SensorManager mySensorManager;
    List<Sensor> deviceSensor;

    Sensor gyro, light, acc;
    ToneGenerator toneG;
    Date flatCounterStart;
    boolean flatFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            if(flatFirst) {
                boolean alarm = checkTime(Calendar.getInstance().getTime());
            }else{
                //check if phone is flat
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

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public boolean checkTime(Date d){
//        if(d = flatCounterStart.add()){
//
//        }

        return true;
    }

}
