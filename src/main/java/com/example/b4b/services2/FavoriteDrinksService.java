package com.example.restaurant.services2;

import com.example.restaurant.models2.Drinks;
import com.example.restaurant.models2.FavoriteDrinks;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteDrinksService {
    public void addToFavs(String id, String id2) throws  ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Accounts").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        String username = document.getString("username");


        DocumentReference drinkRef = db.collection("Drinks").document(id2);
        ApiFuture<DocumentSnapshot> future1 = drinkRef.get();
        DocumentSnapshot doc = future1.get();
        Double drinkID_foreign = doc.getDouble("drinkID");

        Double numRef = 5.00;

        DocumentReference newRef = db.collection("FavoriteDrinks").document();
        FavoriteDrinks newFav = new FavoriteDrinks(newRef.getId(), numRef, drinkID_foreign, username);
        newRef.set(newFav);
        db.collection("FavoriteDrinks").add(newFav);

    }
    public ArrayList<Drinks> getFavsbyUserID(String id) throws ExecutionException, InterruptedException {
        ArrayList<FavoriteDrinks> favoriteDrinks = new ArrayList<>();
        ArrayList<Drinks> drinks = new ArrayList<>();
        // make another arraylist of drinks

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Accounts").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        String username = document.getString("username");


        ApiFuture<QuerySnapshot> future2 = db.collection("FavoriteDrinks")
                .whereEqualTo("username", username)
                .get();
        List<QueryDocumentSnapshot> documents = future2.get().getDocuments();

        //something needs to be done here OR maybe wait until front end where I get the drinkID from
        // the axios call and it into getDrinkbyID function
        for (QueryDocumentSnapshot documentx: documents) {
            String drinkID_foreign = documentx.getString("drinkID");

            ApiFuture<QuerySnapshot> future3 = db.collection("Drinks").whereEqualTo("drinkID", drinkID_foreign).get();
            List<QueryDocumentSnapshot> docs = future3.get().getDocuments();

            for (QueryDocumentSnapshot documentY: docs) {
                drinks.add(documentY.toObject(Drinks.class));
            }

            favoriteDrinks.add(documentx.toObject(FavoriteDrinks.class));
        }

        return drinks;
    }


}
