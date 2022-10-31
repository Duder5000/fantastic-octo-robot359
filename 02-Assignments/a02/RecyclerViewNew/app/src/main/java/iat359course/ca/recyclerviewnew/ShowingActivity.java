package iat359course.ca.recyclerviewnew;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowingActivity extends AppCompatActivity implements SensorEventListener {

    TextView showData;

    SensorManager mySensorManager;

    Sensor selectedSenor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        showData = (TextView) findViewById(R.id.showData);
        Bundle b = getIntent().getExtras(); //sensor ID
        int sensorID = b.getInt("dataToShow");

        selectedSenor = mySensorManager.getDefaultSensor(sensorID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySensorManager.registerListener(this, selectedSenor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        String name = event.sensor.getName();
        float[] sensorEvents = event.values;
        StringBuilder toShow = new StringBuilder(event.sensor.getName()).append(" new values: ");
        for(float i : sensorEvents){
           toShow.append(name + ": " + i + "\n");
        }
        showData.setText(toShow.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}