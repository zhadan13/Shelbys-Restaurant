package com.shelby.restaurant.shelbysrestaurant.service.order.impl;

import com.shelby.restaurant.shelbysrestaurant.controller.order.resource.OrderCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.exception.OrderNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.exception.ValidationException;
import com.shelby.restaurant.shelbysrestaurant.mapper.order.OrderMapper;
import com.shelby.restaurant.shelbysrestaurant.model.order.Order;
import com.shelby.restaurant.shelbysrestaurant.model.order.OrderStatus;
import com.shelby.restaurant.shelbysrestaurant.repository.order.OrderRepository;
import com.shelby.restaurant.shelbysrestaurant.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order createOrder(OrderCreateRequest createRequest) {
        log.info("Creating new order");
        Order order = Order.builder().build();
        orderMapper.mapOrderCreateRequestToOrder(createRequest, order);
        return orderRepository.save(order);
    }

    @Override
    public void updateOrderStatus(String orderId, String orderStatus) {
        log.info("Updating status for order with id {}", orderId);
        if (Arrays.stream(OrderStatus.values()).anyMatch(status -> status.name().equalsIgnoreCase(orderStatus))) {
            orderRepository.findById(orderId)
                    .ifPresentOrElse(order -> orderRepository.updateOrderStatusAndUpdatedAt(orderId, OrderStatus.valueOf(orderStatus), LocalDateTime.now()), () -> {
                        log.error("Order with id {} not found", orderId);
                        throw new OrderNotFoundException("Order with id " + orderId + " not found!");
                    });
        }
    }

    @Override
    public void updateOrderCompletedAtTime(String orderId, String completedAt) {
        log.info("Updating completed at time for order with id {}", orderId);
        LocalDateTime completedAtTime;
        try {
            completedAtTime = LocalDateTime.parse(completedAt);
        } catch (DateTimeParseException e) {
            log.error("Passed completedAt time not valid");
            throw new ValidationException("Passed completedAt time not valid");
        }
        orderRepository.findById(orderId)
                .ifPresentOrElse(order -> orderRepository.updateOrderCompletedAt(orderId, completedAtTime), () -> {
                    log.error("Order with id {} not found", orderId);
                    throw new OrderNotFoundException("Order with id " + orderId + " not found!");
                });
    }

    @Override
    public Order getOrderById(String orderId) {
        log.info("Retrieving order by id {}", orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found!"));
    }

    @Override
    public List<Order> getAllOrders() {
        log.info("Retrieving all orders");
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllUsersOrders(String userId) {
        log.info("Retrieving all orders for user with id {}", userId);
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteOrder(String orderId) {
        log.info("Deleting order by id {}", orderId);
        orderRepository.findById(orderId)
                .ifPresentOrElse(orderRepository::delete, () -> {
                    log.error("Order with id " + orderId + " not found");
                    throw new OrderNotFoundException("Order with id " + orderId + " not found!");
                });
    }

    @Override
    public void deleteUsersOrders(String userId) {
        log.info("Deleting orders for user with id {}", userId);
        orderRepository.deleteUsersOrders(userId);
    }
}
