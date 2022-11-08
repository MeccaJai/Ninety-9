package com.example.restaurant.models2;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @DocumentId
    private String id;
    private Double messageID;
    private String username;
    private String adminID;
    private Timestamp messDate;
    private String receive;
    private String mContext;
}
