package com.shelby.restaurant.shelbysrestaurant.repository.order;

import com.shelby.restaurant.shelbysrestaurant.model.order.Order;
import com.shelby.restaurant.shelbysrestaurant.model.order.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMongoOperations extends MongoRepository<Order, String> {

    List<Order> findAllByUserId(String userId);

    List<Order> findAllByUserIdAndStatus(String userId, OrderStatus status);
}
