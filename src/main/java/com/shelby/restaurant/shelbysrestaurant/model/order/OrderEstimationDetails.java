package com.shelby.restaurant.shelbysrestaurant.model.order;

import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEstimationDetails {

    private Address address;

    private LocalDateTime targetDeliveryAt;

    private Map<String, Long> products;

    private String details;
}
