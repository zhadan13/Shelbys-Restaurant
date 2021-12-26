package com.shelby.restaurant.shelbysrestaurant.model.ordersproducts;

import com.shelby.restaurant.shelbysrestaurant.model.order.Order;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;
}
