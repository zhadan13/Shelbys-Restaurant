package com.shelby.restaurant.shelbysrestaurant.service.product;

import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductCreateRequest createRequest);

    void updateProduct(Long productId, ProductUpdateRequest updateRequest);

    void updateProductPopularity(Long productId);

    void updateProductIsNewStatus(Long productId, Boolean isNewStatus);

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    void deleteProduct(Long productId);
}
