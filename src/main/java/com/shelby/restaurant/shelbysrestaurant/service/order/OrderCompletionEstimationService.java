package com.shelby.restaurant.shelbysrestaurant.service.order;

import com.shelby.restaurant.shelbysrestaurant.model.order.OrderEstimationDetails;

import java.time.LocalDateTime;

public interface OrderCompletionEstimationService {

    LocalDateTime estimate(OrderEstimationDetails orderEstimationDetails);
}
