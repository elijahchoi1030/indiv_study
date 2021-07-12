package com.example.sensingbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor lin_sen;
    TextView t_acc_x;
    TextView t_acc_y;
    TextView t_acc_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lin_sen = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        t_acc_x = findViewById(R.id.acc_x);
        t_acc_y = findViewById(R.id.acc_y);
        t_acc_z = findViewById(R.id.acc_z);
    }

    public void onStart() {
        super.onStart();


        if(lin_sen != null) {
            sensorManager.registerListener((SensorEventListener) this, lin_sen, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){

            t_acc_x.setText(new DecimalFormat("#.00").format(event.values[0]));
            t_acc_y.setText(new DecimalFormat("#.00").format(event.values[1]));
            t_acc_z.setText(new DecimalFormat("#.00").format(event.values[2]));
        }
    }
}