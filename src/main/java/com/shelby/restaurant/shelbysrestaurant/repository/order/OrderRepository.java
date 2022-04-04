package com.shelby.restaurant.shelbysrestaurant.repository.order;

import com.shelby.restaurant.shelbysrestaurant.model.order.Order;
import com.shelby.restaurant.shelbysrestaurant.model.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderRepository {

    private final MongoTemplate mongoTemplate;

    @Delegate(types = {OrderMongoOperations.class, IncludeOperations.class})
    private final OrderMongoOperations orderMongoOperations;

    public Order updateOrderStatusAndUpdatedAt(String orderId, OrderStatus orderStatus, LocalDateTime updatedAt) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(orderId)),
                new Update().set("status", orderStatus).set("updated_at", updatedAt),
                Order.class);
    }

    public Order updateOrderCompletedAt(String orderId, LocalDateTime completedAt) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(orderId)),
                Update.update("completed_at", completedAt),
                Order.class);
    }

    public List<Order> deleteUsersOrders(String userId) {
        return mongoTemplate.findAllAndRemove(
                Query.query(Criteria.where("user_id").is(userId)),
                Order.class);
    }

    private abstract static class IncludeOperations implements CrudRepository<Order, String> {

        @Override
        @NonNull
        public abstract <T extends Order> T save(@NonNull T entity);
    }
}
