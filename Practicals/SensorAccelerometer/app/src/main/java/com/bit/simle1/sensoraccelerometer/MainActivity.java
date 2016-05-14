package com.bit.simle1.sensoraccelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SensorManager sm;
    Sensor accel;
    ImageView img;
    float imgX;
    float imgY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager)MainActivity.this.getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(R.drawable.ball);

        imgX = 0;
        imgY = 0;
    }

    public class accelerometerhandler implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            int xVelocity = 0;
            int yVelocity = 0;

            TextView tvX = (TextView) findViewById(R.id.tvX);
            TextView tvY = (TextView) findViewById(R.id.tvY);
            TextView tvZ = (TextView) findViewById(R.id.tvZ);

            //I liked seeing the values, so I decided to leave them displaying!
            tvX.setText("X:  " + String.format("%.3f", x));
            tvY.setText("Y:  " + String.format("%.3f", y));
            tvZ.setText("Z:  " + String.format("%.3f", z));

            //Determine velocities
            xVelocity = setVelocity(x);
            yVelocity = setVelocity(y);

            //Raw location setting method
            img.setX(imgX + xVelocity);
            img.setY(imgY + yVelocity);

            //Update img X and Y
            imgX += xVelocity;
            imgY += yVelocity;

            //Force the view to redraw itself
            img.invalidate();

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    private int setVelocity(float accelAxisVal){
        int returnVelocity = 0;

        if(accelAxisVal > 0) {
            if (accelAxisVal > 1) {
                returnVelocity = 3;
            }
            if (accelAxisVal > 4) {
                returnVelocity = 5;
            }
            if (accelAxisVal > 7) {
                returnVelocity = 7;
            }

            return returnVelocity;
        }
        else if(accelAxisVal < 0.0){
            if(accelAxisVal < -1){
                returnVelocity = -3;
            }
            if(accelAxisVal < -4){
                returnVelocity = -5;
            }
            if(accelAxisVal < -7){
                returnVelocity = -7;
            }

            return returnVelocity;
        }

        return returnVelocity;
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
