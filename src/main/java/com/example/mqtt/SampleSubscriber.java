package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SampleSubscriber implements MqttCallback{

    public SampleSubscriber() {

    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection is lost " + cause.getStackTrace());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //that provide the content of the message--is in bytes so I am converting to string
        String content = new String(message.getPayload());
        System.out.println("SimpleSubscriber receiving "+ content + " for topic "+ topic + " with qos " +message.getQos());
    }
    

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Delivery is complete " + token.isComplete());
    }

}