package com.example.sensortest;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener
{
	
	private SensorManager mySensorManager = null; 
	private Sensor myAccelerometer = null;
	
	private TextView sensorsTextView; //TextView which will hold list of all sensors
	private EditText accelXEditText; //display acceleration on X
	private EditText accelYEditText; //display acceleration on Y
	private EditText accelZEditText; //display acceleration on Z
	
	
	float[] accel_vals = new float[3]; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
	
		//get references to the layout components
		sensorsTextView = (TextView) findViewById(R.id.sensorsTextView);
		accelXEditText = (EditText)findViewById(R.id.accelXEditText); 
		accelYEditText = (EditText)findViewById(R.id.accelYEditText); 
		accelZEditText = (EditText)findViewById(R.id.accelZEditText);
		
		//get reference to sensor and attach a listener - (acquire sensors late - release early) 
		
		mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
				
		//get reference to the accelerometer sensor
		myAccelerometer = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				
		List<Sensor> mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);
		
		for (int i = 1; i < mList.size(); i++) 
		{
			sensorsTextView.append("\n" + mList.get(i).getName());
		}
	}
	
	@Override
    protected void onResume() {
        super.onResume();
      //listener for the accelerometer sensor
      mySensorManager.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
	
	@Override
	protected void onPause() {
		//unregister listener - release the sensor 
		 mySensorManager.unregisterListener(this);
		super.onPause();
	}



	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		int type = event.sensor.getType(); 
		
		if (type == Sensor.TYPE_ACCELEROMETER)
		{
			accel_vals =event.values; 
			accelXEditText.setText("AccelX: " + accel_vals[0]);
			accelYEditText.setText("AccelY: " + accel_vals[1]);
			accelZEditText.setText("AccelZ: " + accel_vals[2]);
		}
		
	}

}

	


