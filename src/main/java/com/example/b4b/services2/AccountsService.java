package com.example.restaurant.services2;

import com.example.restaurant.models2.Accounts;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AccountsService {

    public ArrayList<Accounts> getUserAccounts() throws ExecutionException, InterruptedException {
        ArrayList<Accounts> users = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Accounts").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document: documents) {
            users.add(document.toObject(Accounts.class));
        }

        return users;
    }

    public ArrayList<Accounts> getAllForSearch(String query) throws ExecutionException, InterruptedException {
        ArrayList<Accounts> accountSearch = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Accounts").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document: documents) {
            String userQuery = document.getString("username");

            if (userQuery.contains(query)){
                accountSearch.add(document.toObject(Accounts.class));
            }
        }

        return accountSearch;
    }
    public Accounts getAccountbyUN(String username) throws ExecutionException, InterruptedException {
        Accounts account = new Accounts();

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Accounts")
                .whereEqualTo("username", username)
                .get();

        List<QueryDocumentSnapshot> userDoc = future.get().getDocuments();


        if(userDoc.get(0) != null) {
            account = userDoc.get(0).toObject(Accounts.class);
        }

        return account;

    }

    public void deactivateAccount(String username) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Accounts")
                .whereEqualTo("username", username)
                .get();

        List<QueryDocumentSnapshot> userDoc = future.get().getDocuments();
        for (QueryDocumentSnapshot document: userDoc) {
            String docID = document.getId();

            DocumentReference docRef = db.collection("Accounts").document(docID);
            ApiFuture<WriteResult> future2 = docRef.update("isActive", false);

            WriteResult result = future2.get();
        }
    }

    public void activateAccount(String username) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("Accounts")
                .whereEqualTo("username", username)
                .get();

        List<QueryDocumentSnapshot> userDoc = future.get().getDocuments();
        for (QueryDocumentSnapshot document: userDoc) {
            String docID = document.getId();

            DocumentReference docRef = db.collection("Accounts").document(docID);
            ApiFuture<WriteResult> future2 = docRef.update("isActive", true);

            WriteResult result = future2.get();
        }
    }


}
