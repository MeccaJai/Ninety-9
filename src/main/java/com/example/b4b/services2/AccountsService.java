package com.example.restaurant.services2;

import com.example.restaurant.models2.Accounts;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.protobuf.Api;
import org.springframework.stereotype.Service;

import java.lang.reflect.Executable;
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

    public Accounts getAccount(String id) throws ExecutionException, InterruptedException {
        Accounts account = new Accounts();

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Accounts").document(id);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if(document.exists()) {
            account = document.toObject(Accounts.class);
        }

        return account;

    }

    public void deactivateAccount(String id) throws ExecutionException, InterruptedException {
        Accounts account = new Accounts();

        Firestore db = FirestoreClient.getFirestore();

        db.collection("Accounts").document(id).update("isActive", false);
    }
}
