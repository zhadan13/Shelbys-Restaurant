package com.shelby.restaurant.shelbysrestaurant.service.product.impl;

import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.exception.ProductNotFoundException;
import com.shelby.restaurant.shelbysrestaurant.mapper.product.ProductMapper;
import com.shelby.restaurant.shelbysrestaurant.model.product.Category;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import com.shelby.restaurant.shelbysrestaurant.repository.product.ProductRepository;
import com.shelby.restaurant.shelbysrestaurant.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product createProduct(ProductCreateRequest createRequest) {
        log.info("Creating new product");
        Product product = Product.builder().build();
        productMapper.mapProductCreateRequestToProduct(createRequest, product);
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(String productId, ProductUpdateRequest updateRequest) {
        log.info("Updating product with id {}", productId);
        productRepository.findById(productId)
                .ifPresentOrElse(product -> {
                    productMapper.mapProductUpdateRequestToProduct(updateRequest, product);
                    productRepository.save(product);
                }, () -> {
                    log.error("Product with id {} not found", productId);
                    throw new ProductNotFoundException("Product with id " + productId + " not found!");
                });
    }

    @Async
    @Override
    public void updateProductPopularity(String productId) {
        log.info("Updating popularity for product with id {}", productId);
        productRepository.findById(productId)
                .ifPresentOrElse(product -> {
                    product.setPopularity(product.getPopularity() + 1);
                    productRepository.save(product);
                }, () -> {
                    log.error("Product with id {} not found", productId);
                    throw new ProductNotFoundException("Product with id " + productId + " not found!");
                });
    }

    @Async
    @Override
    public void updateProductIsNewStatus(String productId, Boolean isNewStatus) {
        log.info("Updating isNew status for product with id {}", productId);
        productRepository.findById(productId)
                .ifPresentOrElse(product -> productRepository.updateIsNewStatus(productId, isNewStatus), () -> {
                    log.error("Product with id {} not found", productId);
                    throw new ProductNotFoundException("Product with id " + productId + " not found!");
                });
    }

    @Override
    public Product getProductById(String productId) {
        log.info("Retrieving product by id {}", productId);
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found!"));
    }

    @Override
    public List<Product> getAllProducts() {
        log.info("Retrieving all products");
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        log.info("Retrieving all products by category {}", category);
        if (Arrays.stream(Category.values()).anyMatch(category1 -> category1.name().equalsIgnoreCase(category))) {
            return productRepository.findAllByCategory(Category.valueOf(category));
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteProduct(String productId) {
        log.info("Deleting product by id {}", productId);
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete, () -> {
                    log.error("Product with id " + productId + " not found");
                    throw new ProductNotFoundException("Product with id " + productId + " not found!");
                });
    }
}
