package com.shelby.restaurant.shelbysrestaurant.repository.product;

import com.shelby.restaurant.shelbysrestaurant.model.product.Category;
import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.isNew = ?2 WHERE p.id = ?1")
    void updateIsNewStatus(Long productId, Boolean isNewStatus);

    List<Product> findAllByCategory(Category category);
}
