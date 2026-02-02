package com.example.orderservice.Controller;

import com.example.orderservice.Entity.Order;
import com.example.orderservice.Service.OrderEventPublisher;
import com.example.orderservice.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final OrderEventPublisher publisher;

    public OrderController(OrderService service, OrderEventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order saved = service.createOrder(order);
        if(order.getId()%2==0)
        {
            publisher.publishOrderCreatedEvent(saved);
        }
        return saved;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @GetMapping()
    public List<Order> getAllOrders(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return service.getOrdersByUserId(userId);
        }
        return service.getAllOrders();
    }
}