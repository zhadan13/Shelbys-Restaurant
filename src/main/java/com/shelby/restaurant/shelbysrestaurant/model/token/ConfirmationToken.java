package com.shelby.restaurant.shelbysrestaurant.model.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("confirmation_tokens")
@CompoundIndexes({@CompoundIndex(name = "id_user_id", def = "{'id' : 1, 'user_id': 1}")})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {

    @Id
    private String id;

    @Indexed(unique = true, background = true)
    private String userId;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;
}
