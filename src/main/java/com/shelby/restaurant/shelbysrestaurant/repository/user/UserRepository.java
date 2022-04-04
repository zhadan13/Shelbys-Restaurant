package com.shelby.restaurant.shelbysrestaurant.repository.user;

import com.shelby.restaurant.shelbysrestaurant.model.user.User;
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
public class UserRepository {

    private final MongoTemplate mongoTemplate;

    @Delegate(types = {UserMongoOperations.class, IncludeOperations.class})
    private final UserMongoOperations userMongoOperations;

    public User enableUser(String email) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("email").is(email)),
                Update.update("enabled", true),
                User.class);
    }

    private abstract static class IncludeOperations implements CrudRepository<User, String> {

        @Override
        @NonNull
        public abstract <T extends User> T save(@NonNull T entity);
    }
}
