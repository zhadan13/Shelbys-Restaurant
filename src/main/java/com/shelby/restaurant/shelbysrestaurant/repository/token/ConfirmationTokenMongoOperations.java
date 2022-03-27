package com.shelby.restaurant.shelbysrestaurant.repository.token;

import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenMongoOperations extends MongoRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByToken(String token);

    Optional<ConfirmationToken> findByUserId(String userId);
}
