package com.shelby.restaurant.shelbysrestaurant.model.order;

import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document("orders")
@CompoundIndexes({@CompoundIndex(name = "email_password", def = "{'email' : 1, 'password': 1}")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    @Indexed(unique = true, background = true)
    private String userId;

    private OrderStatus status;

    private Address address;

    private Payment payment;

    private Double cost;

    private LocalDateTime date;

    @DBRef
    private Map<Product, Long> products;
}
