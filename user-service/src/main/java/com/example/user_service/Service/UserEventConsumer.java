package com.example.user_service.Service;

import com.example.user_service.Entity.User;
import com.example.user_service.Repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserEventConsumer {

    private final UserRepository repository;

    public UserEventConsumer(UserRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "order-created-topic", groupId = "user-service-group")
    public void consumeOrderEvent(Map<String, Object> event) {
        Long userId = ((Number) event.get("userId")).longValue();
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setSomeFlag(true);
        repository.save(user);
        System.out.println("Updated user " + userId);
    }
}

