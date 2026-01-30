package com.example.user_service.Controller;

import com.example.user_service.Entity.Order;
import com.example.user_service.Entity.User;
import com.example.user_service.Service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final RestTemplate restTemplate;

    public UserController(UserService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getUserOrders(@PathVariable Long id) {
        Order[] orders = restTemplate.getForObject(
                "http://order-service:9091/orders?userId=" + id,
                Order[].class
        );
        return Arrays.asList(orders);
    }

}
