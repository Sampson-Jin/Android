package com.example.sensor;

import android.app.Activity;
import android.content.pm.PackageInstaller.Session;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	private SensorManager sensorManager;
	private Sensor sensor;
	private MyListener myListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
		sensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		sensorManager.registerListener( myListener=new MyListener(), sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	private class MyListener implements SensorEventListener{

		@Override
		public void onSensorChanged(SensorEvent event) {
			float 光强度值=event.values[0];
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		sensorManager.unregisterListener(myListener, sensor);
		super.onDestroy();
	}
}
