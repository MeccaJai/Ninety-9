package com.example.restaurant.models2;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @DocumentId
    private String id;
    private Double commentId;
    private Double drinkID;
    private String username;
    private String cContext;
}
