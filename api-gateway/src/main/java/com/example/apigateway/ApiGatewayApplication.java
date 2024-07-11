package com.example.apigateway;

import com.example.apigateway.event.OrderPlacedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

//	@KafkaListener(topics = "notificationTopic")
//	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
//		System.out.println("Received notification for order - " + orderPlacedEvent.getOrderNumber());
//	}

//	@KafkaListener(topics = "notificationTopicString")
//	public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
//		System.out.println("Received notification for order - " + orderPlacedEvent.getOrderNumber());
//	}
}
