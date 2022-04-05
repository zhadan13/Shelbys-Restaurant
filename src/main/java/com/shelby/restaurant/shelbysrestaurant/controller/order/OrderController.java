package com.shelby.restaurant.shelbysrestaurant.controller.order;

import com.shelby.restaurant.shelbysrestaurant.controller.order.resource.OrderCreateRequest;
import com.shelby.restaurant.shelbysrestaurant.model.order.Order;
import com.shelby.restaurant.shelbysrestaurant.model.order.OrderEstimationDetails;
import com.shelby.restaurant.shelbysrestaurant.security.Permissions;
import com.shelby.restaurant.shelbysrestaurant.service.order.OrderCompletionEstimationService;
import com.shelby.restaurant.shelbysrestaurant.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@PreAuthorize(Permissions.GLOBAL_SCOPE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderCompletionEstimationService orderCompletionEstimationService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderCreateRequest createRequest) {
        Order order = orderService.createOrder(createRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(@RequestParam(name = "user", required = false) String userId) {
        if (userId != null) {
            return ResponseEntity.ok(orderService.getAllUsersOrders(userId));
        } else {
            return ResponseEntity.ok(orderService.getAllOrders());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/completion")
    public ResponseEntity<?> updateOrderCompletion(@PathVariable String id, @RequestParam String completedAt) {
        orderService.updateOrderCompletedAtTime(id, completedAt);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/estimate")
    public ResponseEntity<LocalDateTime> estimateOrderCompletion(@RequestBody @Valid OrderEstimationDetails orderEstimationDetails) {
        return ResponseEntity.ok(orderCompletionEstimationService.estimate(orderEstimationDetails));
    }
}
