package com.example.restaurant.models2;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @DocumentId
    private String id;
    private String uuid;
    private String account_type;
    private String email;
    private String fName;
    private String lName;
    private Boolean isActive;
    private Boolean isVerified;
    private String username;
}
