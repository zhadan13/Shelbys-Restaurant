package com.shelby.restaurant.shelbysrestaurant.repository.product;

import com.shelby.restaurant.shelbysrestaurant.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRepository {

    private final MongoTemplate mongoTemplate;

    @Delegate(types = {ProductMongoOperations.class, IncludeOperations.class})
    private final ProductMongoOperations productMongoOperations;

    public Product updateIsNewStatus(String productId, Boolean isNewStatus) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(productId)),
                Update.update("is_new", isNewStatus),
                Product.class);
    }

    private abstract static class IncludeOperations implements CrudRepository<Product, String> {

        @Override
        @NonNull
        public abstract <T extends Product> T save(@NonNull T entity);
    }
}
