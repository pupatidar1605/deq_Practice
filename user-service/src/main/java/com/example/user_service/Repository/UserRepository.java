package com.example.user_service.Repository;

import com.example.user_service.Entity.Order;
import com.example.user_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<Order> findByUserId(Long userId);
}
