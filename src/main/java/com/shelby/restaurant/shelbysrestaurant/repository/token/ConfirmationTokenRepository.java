package com.shelby.restaurant.shelbysrestaurant.repository.token;

import com.shelby.restaurant.shelbysrestaurant.model.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Query("SELECT t FROM ConfirmationToken t WHERE t.user = ?1")
    Optional<ConfirmationToken> findByUser(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken t SET t.confirmedAt = ?2 WHERE t.token = ?1")
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
