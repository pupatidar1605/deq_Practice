package com.example.orderservice.Service;

import com.example.orderservice.Entity.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreatedEvent(Order order) {
        Map<String, Object> event = new HashMap<>();
        event.put("orderId", order.getId());
        event.put("userId", order.getUserId());
        event.put("price", order.getPrice());

        kafkaTemplate.send("order-created-topic", event);
        System.out.println("Order event published for order " + order.getId());
    }
}

