package com.shelby.restaurant.shelbysrestaurant.controller.order.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import com.shelby.restaurant.shelbysrestaurant.model.order.OrderStatus;
import com.shelby.restaurant.shelbysrestaurant.model.payment.PaymentType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class OrderCreateRequest {

    @JsonProperty(value = "userId", required = true)
    @NotNull
    private String userId;

    @JsonProperty(value = "status", required = true)
    @NotNull
    private OrderStatus status;

    @JsonProperty(value = "address", required = true)
    @NotNull
    private Address address;

    @JsonProperty(value = "paymentType", required = true)
    @NotNull
    private PaymentType paymentType;

    @JsonProperty(value = "cost", required = true)
    @NotNull
    private Double cost;

    @JsonProperty(value = "deliveryCost", required = true)
    @NotNull
    private Double deliveryCost;

    @JsonProperty(value = "paymentId", required = true)
    @NotNull
    private String paymentId;

    @JsonProperty(value = "estimatedCompleteAt", required = true)
    @NotNull
    private LocalDateTime estimatedCompleteAt;

    @JsonProperty(value = "targetDeliveryAt", required = true)
    @NotNull
    private LocalDateTime targetDeliveryAt;

    @JsonProperty(value = "products", required = true)
    @NotNull
    private Map<String, Long> products;

    @JsonProperty(value = "details")
    private String details;
}
