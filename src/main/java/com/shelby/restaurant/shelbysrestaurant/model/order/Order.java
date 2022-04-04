package com.shelby.restaurant.shelbysrestaurant.model.order;

import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import com.shelby.restaurant.shelbysrestaurant.model.payment.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document("orders")
@CompoundIndexes({@CompoundIndex(name = "order_id_user_id", def = "{'id' : 1, 'user_id': 1}")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    @Indexed(background = true)
    private String userId;

    private OrderStatus status;

    private Address address;

    private PaymentType paymentType;

    private Double cost;

    private Double deliveryCost;

    private String paymentId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime estimatedCompleteAt;

    private LocalDateTime completedAt;

    private LocalDateTime targetDeliveryAt;

    private Map<String, Long> products;

    private String details;
}
