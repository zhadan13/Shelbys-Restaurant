package com.shelby.restaurant.shelbysrestaurant.model.order;

import com.shelby.restaurant.shelbysrestaurant.model.address.Address;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false)
    private LocalDateTime date;

    @Transient
    private Map<Product, Integer> products;
}
