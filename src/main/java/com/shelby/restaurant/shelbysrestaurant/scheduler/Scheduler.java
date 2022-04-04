package com.shelby.restaurant.shelbysrestaurant.scheduler;

import com.shelby.restaurant.shelbysrestaurant.model.order.OrderStatus;
import com.shelby.restaurant.shelbysrestaurant.service.order.OrderService;
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
    private final OrderService orderService;

    @Scheduled(cron = "${scheduler.cron.products.update}")
    public void refreshProductsIsNewStatus() {
        log.info("Refreshing isNewStatus for products");
        LocalDateTime currentDateTime = LocalDateTime.now();
        productService.getAllProducts()
                .stream()
                .filter(product -> product.getAddedAt().plusWeeks(3).isBefore(currentDateTime))
                .forEach(product -> productService.updateProductIsNewStatus(product.getId(), false));
    }

    @Scheduled(cron = "${scheduler.cron.orders.old.delete}")
    public void clearOldOrders() {
        log.info("Clearing orders older two years");
        LocalDateTime currentDateTime = LocalDateTime.now();
        orderService.getAllOrders()
                .stream()
                .filter(order -> order.getCompletedAt().plusYears(2).isBefore(currentDateTime))
                .forEach(order -> orderService.deleteOrder(order.getId()));
    }

    @Scheduled(cron = "${scheduler.cron.orders.inactive.status.delete}")
    public void clearRejectedAndCanceledOrders() {
        log.info("Clearing orders older three month in status REJECTED or CANCELED");
        LocalDateTime currentDateTime = LocalDateTime.now();
        orderService.getAllOrders()
                .stream()
                .filter(order -> order.getUpdatedAt().plusMonths(3).isBefore(currentDateTime)
                        && (order.getStatus().equals(OrderStatus.REJECTED) || order.getStatus().equals(OrderStatus.CANCELED)))
                .forEach(order -> orderService.deleteOrder(order.getId()));
    }
}
