package iat359course.ca.recyclerviewnew;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
    RecyclerView myRecycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

//    ArrayList<String> sensorStrings;
//    ArrayList<Integer> sensorInt;
    ArrayList<Sensor> sensorArr;

    SensorManager mySensorManager;
    List<Sensor> deviceSensor;


    Sensor gyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        deviceSensor = mySensorManager.getSensorList(Sensor.TYPE_ALL);

//        sensorStrings = new ArrayList<String>();
//        sensorInt = new ArrayList<Integer>();
        sensorArr = new ArrayList<Sensor>();

        for (Sensor j: deviceSensor){
//            Log.d("test", "Sensor: " + j.getName());
//            sensorStrings.add(j.getName());
//            sensorInt.add(j.getType());
            sensorArr.add(j);
        }

        gyro = mySensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        StringBuilder sensorMesage = new StringBuilder(event.sensor.getName()).append(" new values: ");

        for(float value : event.values){
            sensorMesage.append("[").append(value).append("]");
        }

        sensorMesage.append(" with accuracy ").append(event.accuracy);
        sensorMesage.append(" at timestamp ").append(event.timestamp);

        sensorMesage.append(".");

        Log.d("test", String.valueOf(sensorMesage));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
