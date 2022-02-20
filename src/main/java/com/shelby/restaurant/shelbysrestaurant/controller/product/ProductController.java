package com.shelby.restaurant.shelbysrestaurant.controller.product;

import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.controller.product.resource.ProductUpdateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import com.shelby.restaurant.shelbysrestaurant.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductCreateRequest createRequest) {
        Product product = productService.createProduct(createRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(Long.valueOf(id)));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam(name = "category") String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,
                                           @RequestBody @Valid ProductUpdateRequest updateRequest) {
        productService.updateProduct(Long.valueOf(id), updateRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/popularity")
    public ResponseEntity<?> updateProductPopularity(@PathVariable String id) {
        productService.updateProductPopularity(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateProductIsNewStatus(@PathVariable String id, @RequestParam String isNewStatus) {
        productService.updateProductIsNewStatus(Long.valueOf(id), Boolean.valueOf(isNewStatus));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}
