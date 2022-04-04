package com.shelby.restaurant.shelbysrestaurant.service.order.impl;

import com.shelby.restaurant.shelbysrestaurant.model.order.OrderEstimationDetails;
import com.shelby.restaurant.shelbysrestaurant.service.order.OrderCompletionEstimationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCompletionEstimationServiceImpl implements OrderCompletionEstimationService {

    @Override
    public LocalDateTime estimate(OrderEstimationDetails orderEstimationDetails) {
        log.info("Estimating order completion time");
        // TODO: Implement estimation logic
        return LocalDateTime.now().plusMinutes(90);
    }
}
