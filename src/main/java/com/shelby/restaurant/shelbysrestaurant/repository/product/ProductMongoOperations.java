package com.shelby.restaurant.shelbysrestaurant.repository.product;

import com.shelby.restaurant.shelbysrestaurant.model.product.Category;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMongoOperations extends MongoRepository<Product, String> {

    List<Product> findAllByCategory(Category category);
}
