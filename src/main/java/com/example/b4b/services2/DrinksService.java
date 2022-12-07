package com.example.restaurant.services2;

import com.example.restaurant.models2.Drinks;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
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

    public ArrayList<Drinks> getAllForSearch(String query) throws ExecutionException, InterruptedException {
        ArrayList<Drinks> drinksSearch = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document: documents) {
            String drinkQuery = document.getString("drinkName");

            if (drinkQuery.contains(query)){
                drinksSearch.add(document.toObject(Drinks.class));
            }
        }

        return drinksSearch;
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

            if (category.equals("Ordinary Drink")){
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

    //updated
    public void approveDrink(Integer id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks")
                .whereEqualTo("drinkID", id)
                .get();

        List<QueryDocumentSnapshot> drinkDoc = future.get().getDocuments();
        for (QueryDocumentSnapshot document: drinkDoc) {
            String docID = document.getId();

            DocumentReference drinkRef = db.collection("Drinks").document(docID);
            ApiFuture<WriteResult> future2 = drinkRef.update("approved", true);

            WriteResult result = future2.get();
        }
    }

    //updated
    public void removeDrink(Integer id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks")
                .whereEqualTo("drinkID", id)
                .get();

        List<QueryDocumentSnapshot> drinkDoc = future.get().getDocuments();
        for (QueryDocumentSnapshot document: drinkDoc) {
            String docID = document.getId();

            DocumentReference drinkRef = db.collection("Drinks").document(docID);
            ApiFuture<WriteResult> future2 = drinkRef.delete();

            WriteResult result = future2.get();
        }
    }

    //updated
    public void editRecipe(Integer id, String name, String info) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks")
                .whereEqualTo("drinkID", id)
                .get();

        List<QueryDocumentSnapshot> drinkDocs = future.get().getDocuments();
        for (QueryDocumentSnapshot document: drinkDocs) {
            String docID = document.getId();

            DocumentReference drinkRef = db.collection("Drinks").document(docID);
            ApiFuture<WriteResult> future2 = drinkRef.update(name, info);

            WriteResult result = future2.get();
        }
    }

    //updated
    public ArrayList<Drinks> drinkHistory(String username) throws ExecutionException, InterruptedException {
        ArrayList<Drinks> drinksHist = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Drinks")
                .whereEqualTo("username", username)
                .get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot documentX: documents) {
            drinksHist.add(documentX.toObject(Drinks.class));
        }

        return drinksHist;
    }

    static Double counter = 1.00;
    static Double rolling_rating;
    //updated
    public void updateRating(String id, Double newRate) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference drinkRef = db.collection("Drinks").document(id);
        ApiFuture<DocumentSnapshot> drinkDoc = drinkRef.get();
        DocumentSnapshot document = drinkDoc.get();
        Double current_rate = document.getDouble("rating");

        Double newRate_;
        if (counter <= 2){
            rolling_rating = rolling_rating + newRate;
        }
        else {
            rolling_rating = current_rate + newRate;
        }
        counter = counter + 1;
        newRate_ = rolling_rating / counter;

        ApiFuture<WriteResult> future = drinkRef.update("rating", newRate_);

        WriteResult result = future.get();

    }

    public ArrayList<Drinks> displayUNPROV () throws ExecutionException, InterruptedException {
        ArrayList<Drinks> unnaprovedDrinks = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();


        ApiFuture<QuerySnapshot> future = db.collection("Drinks").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document: documents) {
            Boolean drinkQuery = document.getBoolean("approved");

            if (drinkQuery == false){
                unnaprovedDrinks.add(document.toObject(Drinks.class));
            }
        }

        return unnaprovedDrinks;
    }
}
