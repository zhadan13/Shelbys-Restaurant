package com.shelby.restaurant.shelbysrestaurant.service.order;

import com.shelby.restaurant.shelbysrestaurant.controller.order.resource.OrderCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.order.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderCreateRequest createRequest);

    void updateOrderStatus(String orderId, String orderStatus);

    void updateOrderCompletedAtTime(String orderId, String completedAt);

    Order getOrderById(String orderId);

    List<Order> getAllOrders();

    List<Order> getAllUsersOrders(String userId);

    void deleteOrder(String orderId);

    void deleteUsersOrders(String userId);
}
