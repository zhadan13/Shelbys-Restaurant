package com.shelby.restaurant.shelbysrestaurant.service.product;

import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductCreateRequest createRequest);

    void updateProduct(String productId, ProductUpdateRequest updateRequest);

    void updateProductPopularity(String productId);

    void updateProductIsNewStatus(String productId, Boolean status);

    Product getProductById(String productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    void deleteProduct(String productId);
}
