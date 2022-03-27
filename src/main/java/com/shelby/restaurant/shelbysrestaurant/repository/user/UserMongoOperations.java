package com.shelby.restaurant.shelbysrestaurant.repository.user;

import com.shelby.restaurant.shelbysrestaurant.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoOperations extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<User> findUserByEmailAndPassword(String email, String password);
}
