package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber{
	
	
	
	public static void main(String[] args) {
		
		
		
		String broker = "tcp://localhost:1883";
		String clientID = "SimpleSubscriber";
		MemoryPersistence persistence = new MemoryPersistence();	
		
		try {
			/*
			 * getting the client
			 */
			MqttClient client = new MqttClient(broker, clientID, persistence);
			
			/*
			 * specify connection options
			 */			
			MqttConnectOptions con_options = new MqttConnectOptions();
			con_options.setCleanSession(true);
			client.setCallback(new SampleSubscriber());
			
			/*
			 * connect
			 */			
			System.out.println("Subscriber connecting to the broker " + broker);
			client.connect(con_options);
			System.out.println("Connected...");
			
			/*
			 * SUBSCRIBE TO TOPICS
			 * football seperate
			 */			
			client.subscribe("/sports/football");	
			
			client.subscribe("/sports/#");	
			
			client.subscribe("/news/#");	
			
			
		} catch (MqttException e) {
			System.out.println("reason " + e.getReasonCode());
			System.out.println("message " + e.getReasonCode());
			System.out.println("location " + e.getLocalizedMessage());
			System.out.println("cause " + e.getCause());
			System.out.println("trace " + e.getStackTrace());
		}
	}
}
