package com.shelby.restaurant.shelbysrestaurant.repository.token;

import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
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

@Component
@RequiredArgsConstructor
public class ConfirmationTokenRepository {

    private final MongoTemplate mongoTemplate;

    @Delegate(types = {ConfirmationTokenMongoOperations.class, IncludeOperations.class})
    private final ConfirmationTokenMongoOperations confirmationTokenMongoOperations;

    public ConfirmationToken updateConfirmedAt(String token, LocalDateTime confirmedAt) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("token").is(token)),
                Update.update("confirmed_at", confirmedAt),
                ConfirmationToken.class);
    }

    private abstract static class IncludeOperations implements CrudRepository<ConfirmationToken, String> {

        @Override
        @NonNull
        public abstract <T extends ConfirmationToken> T save(@NonNull T entity);
    }
}
