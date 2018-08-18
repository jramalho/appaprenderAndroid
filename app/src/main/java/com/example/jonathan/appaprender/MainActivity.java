package com.example.jonathan.appaprender;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.SQLException;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    mqttHelper mqttHelper;
    String cmd = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b1 = (Button) findViewById(R.id.btn1);
        final Button b2 = (Button) findViewById(R.id.btn2);
        startMqtt();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmd = "00";
                mqttHelper.publishToTopic(cmd);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttHelper.publishToTopic("01");
            }
        });

    }


    private void startMqtt() {

        mqttHelper = new mqttHelper(getApplicationContext());
        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

                Log.w("Debug", "Connected");
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

                Log.w("Debug", mqttMessage.toString());
                //intMqtt(mqttMessage.toString());

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });

    }
    //private void intMqtt(String message){

    }

