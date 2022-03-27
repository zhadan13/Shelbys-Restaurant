package com.shelby.restaurant.shelbysrestaurant.scheduler;

import com.shelby.restaurant.shelbysrestaurant.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@ConditionalOnProperty(name = "scheduler.enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class Scheduler {

    private final ProductService productService;

    @Scheduled(cron = "${scheduler.cron}")
    public void refreshProductsIsNewStatus() {
        log.info("Refreshing isNewStatus for products");
        LocalDateTime currentDateTime = LocalDateTime.now();
        productService.getAllProducts()
                .stream()
                .filter(product -> product.getAddedAt().plusWeeks(3).isBefore(currentDateTime))
                .forEach(product -> productService.updateProductIsNewStatus(product.getId(), false));
    }
}
