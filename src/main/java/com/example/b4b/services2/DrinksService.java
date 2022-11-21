package com.example.restaurant.services2;

import com.example.restaurant.models2.Drinks;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DrinksService {

    public ArrayList<Drinks> getAllDrinks() throws ExecutionException, InterruptedException {
        ArrayList<Drinks> drinks = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document: documents) {
            drinks.add(document.toObject(Drinks.class));
        }

        return drinks;
    }

    public Drinks getDrinkbyID(Integer id) throws ExecutionException, InterruptedException {
        Drinks drink = new Drinks();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks")
                .whereEqualTo("drinkID", id)
                .get();

        List<QueryDocumentSnapshot> drinkDoc = future.get().getDocuments();

        if (drinkDoc.get(0) != null){
            drink = drinkDoc.get(0).toObject(Drinks.class);
        }
        return drink;

    }

    public Drinks getDrinkbyName(String name) throws ExecutionException, InterruptedException {
        Drinks drink = new Drinks();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collectionGroup("Drinks")
                .whereEqualTo("drinkName", name)
                .get();

        List<QueryDocumentSnapshot> drinkDoc = future.get().getDocuments();

        if (drinkDoc.get(0) != null){
            drink = drinkDoc.get(0).toObject(Drinks.class);
        }

        return drink;

    }

    public Map<String, Integer> getCategoryCount() throws ExecutionException, InterruptedException {
        Map<String, Integer> catMap = new HashMap<>();
        ArrayList<Drinks> drinks2 = getAllDrinks();
        Integer[] counts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Drinks drink: drinks2) {
            String category = drink.getDCategory();

            if (category.equals("Ordinary")){
                counts[0] = counts[0] + 1;
            } else if (category.equals("Cocktail")) {
                counts[1] = counts[1] + 1;
            } else if (category.equals("Shake")) {
                counts[2] = counts[2] + 1;
            } else if (category.equals("Other/Unknown")) {
                counts[3] = counts[3] + 1;
            } else if (category.equals("Cocoa")) {
                counts[4] = counts[4] + 1;
            } else if (category.equals("Shot")) {
                counts[5] = counts[5] + 1;
            } else if (category.equals("Coffee/Tea")) {
                counts[6] = counts[6] + 1;
            } else if (category.equals("Homemade Liqueur")) {
                counts[7] = counts[7] + 1;
            } else if (category.equals("Punch/Party Drink")) {
                counts[8] = counts[8] + 1;
            } else if (category.equals("Beer")) {
                counts[9] = counts[9] + 1;
            } else if (category.equals("Soft Drink")) {
                counts[10] = counts[10] + 1;
            }
        }

        catMap.put("Ordinary", counts[0]);
        catMap.put("Cocktail", counts[1]);
        catMap.put("Shake", counts[2]);
        catMap.put("Other/Unknown", counts[3]);
        catMap.put("Cocoa", counts[4]);
        catMap.put("Shot", counts[5]);
        catMap.put("Coffee/Tea", counts[6]);
        catMap.put("Homemade Liqueur", counts[7]);
        catMap.put("Punch/Party Drink", counts[8]);
        catMap.put("Beer", counts[9]);
        catMap.put("Soft Drink", counts[10]);

        return catMap;
    }

    public void approveDrink(String id) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Drinks").document(id);
        ApiFuture<WriteResult> future = docRef.update("approved", true);

        WriteResult result = future.get();
    }

    public void removeDrink(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Drinks").document(id);
        ApiFuture<WriteResult> future = docRef.delete();

        WriteResult result = future.get();

    }

    public void editRecipe(String id, String name, String info) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Drinks").document(id);
        ApiFuture<WriteResult> future = docRef.update(name, info);

        WriteResult result = future.get();
    }
}
