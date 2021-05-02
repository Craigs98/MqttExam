package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublish {	

	public static void main(String[] args) {
		
		//connecting details for the broker for sports publisher
		String topicfootball = "/sports/football" ;
		String contentfootball = "Score is 3-1 to liverpool";
		
		String topiccricket = "/sports/cricket" ;
		String contentcricket = "England lost to Ireland";
		
		String topicregional = "/sports/regional" ;
		String contentregional = "Katie Taylor wins title";
		
		
		//connecting details for the broker for political publisher
		String topicpolitics = "/news/elections" ;
		String contentpolitics = "Sinn Fein leading the votes";
		
		String topicbusiness = "/news/regional" ;
		String contentbusiness = "Intel setup new premises in dublin";
		
		
		
		
		
		String broker = "tcp://localhost:1883";
		int qos = 2;		
		String clientId = "SimplePublisher";	
		
		MemoryPersistence persistence = new MemoryPersistence();		
		
		try {			
			MqttClient client = new MqttClient(broker, clientId, persistence);
			
			/*
			 * lets specify connection options
			 */
			MqttConnectOptions con_options = new MqttConnectOptions();
			
			//if CleanSession is true then all pending publications will be removed
			//otherwise they will not removed and send to the client
			con_options.setCleanSession(true);	
			
			/*
			 * connecting to the broker with specific options
			 */
			System.out.println("Connecting to the broker " + broker);
			client.connect(con_options);
			System.out.println("Connected...");
			
			
			System.out.println("--------------------------------------------------");
			System.out.println("Sports news");
			System.out.println("Publishing message for Football: " + contentfootball);
			System.out.println("Publishing message for Cricket "   + contentcricket);
			System.out.println("Publishing message for Regional "  + contentregional);
			System.out.println("--------------------------------------------------");
			
			
			
			System.out.println("Political news and business");
			System.out.println("Publishing message for Politics: " + contentpolitics);
			System.out.println("Publishing message for Business "   + contentbusiness);
			System.out.println("--------------------------------------------------");
	
			
			/*
			 * NEXT SENT MESSAGE TO A TOPIC
			 */	
			
			
			MqttMessage message = new MqttMessage(contentfootball.getBytes());
			message.setQos(qos);
			client.publish(topicfootball, message);
			System.out.println("Football Message published");
			
			MqttMessage message2 = new MqttMessage(contentcricket.getBytes());
			message.setQos(qos);
			client.publish(topiccricket, message2);
			System.out.println("Cricket Message published");
			
			MqttMessage message3 = new MqttMessage(contentregional.getBytes());
			message.setQos(qos);
			client.publish(topicregional, message3);
			System.out.println("Regional Sports news Message published");
		
			
			MqttMessage message4 = new MqttMessage(contentpolitics.getBytes());
			message.setQos(qos);
			client.publish(topicpolitics, message4);
			System.out.println("political news Message published");
			
			MqttMessage message5 = new MqttMessage(contentbusiness.getBytes());
			message.setQos(qos);
			client.publish(topicbusiness, message5);
			System.out.println("Business news Message published");
			
			/*
			 * disconnect from broker
			 */
			client.disconnect();
			System.out.println("Disconected...");			
			
		} catch (MqttException e) {
			System.out.println("reason " + e.getReasonCode());
			System.out.println("message " + e.getMessage());
			System.out.println("location " + e.getLocalizedMessage());
			System.out.println("cause " + e.getCause());
			System.out.println("trace " + e.getStackTrace());
		}		
	}
}