package com.example.sensingbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor lin_sen;
    private TextView t_acc_x;
    private TextView t_acc_y;
    private TextView t_acc_z;

    private TextView tx;
    private TextView ty;
    private TextView tz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lin_sen = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        t_acc_x = findViewById(R.id.acc_x);
        t_acc_y = findViewById(R.id.acc_y);
        t_acc_z = findViewById(R.id.acc_z);

        tx = findViewById(R.id.textView2);
        ty = findViewById(R.id.textView4);
        tz = findViewById(R.id.textView5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, lin_sen, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){

            t_acc_x.setText(new DecimalFormat("#.0000").format(event.values[0]));
            t_acc_y.setText(new DecimalFormat("#.0000").format(event.values[1]));
            t_acc_z.setText(new DecimalFormat("#.0000").format(event.values[2]));

            if(event.values[0] > 5)
                tx.setTextColor(0xAAFF0000);
            if(event.values[1] > 5)
                ty.setTextColor(0xAAFF0000);
            if(event.values[2] > 5)
                tz.setTextColor(0xAAFF0000);
        }
    }

    public void resetColor(View view){
        tx.setTextColor(Color.parseColor("#000000"));
        ty.setTextColor(Color.parseColor("#000000"));
        tz.setTextColor(Color.parseColor("#000000"));
    }
}