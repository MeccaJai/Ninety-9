package com.example.restaurant.models2;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drinks {
    @DocumentId
    private String id;
    private Double drinkID;
    private String dCategory;
    private String drinkName;
    private String drinkIngred;
    private String drinkInstruct;
    private String drinkPic;
    private String username;
    private Boolean drinkAch;
    private Boolean approved;
}
