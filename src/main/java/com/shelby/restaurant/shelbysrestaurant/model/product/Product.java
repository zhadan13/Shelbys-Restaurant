package com.shelby.restaurant.shelbysrestaurant.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document("products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;

    private String details;

    private Set<String> ingredients;

    private Category category;

    private Double price;

    private Double weight;

    private Integer popularity;

    private Boolean isNew;

    private LocalDateTime addedAt;
}
