package com.bit.simle1.sensoraccelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager sm;
    Sensor accel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    public class accelerometerhandler implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];


            TextView tvX = (TextView) findViewById(R.id.tvX);
            TextView tvY = (TextView) findViewById(R.id.tvY);
            TextView tvZ = (TextView) findViewById(R.id.tvZ);

            tvX.setText("" + x);
            tvY.setText("" + y);
            tvZ.setText("" + z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(new accelerometerhandler(), accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sm.unregisterListener(new accelerometerhandler());
    }
}
