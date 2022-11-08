package com.example.restaurant.services2;

import com.example.restaurant.models2.Messages;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MessagesService {

    public ArrayList<Messages> retrieveMessagesbyAdmin(String id, String id2) throws ExecutionException, InterruptedException {
        ArrayList<Messages> list = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference adminRef = db.collection("AdminAccounts").document(id);
        DocumentReference userRef = db.collection("Accounts").document(id2);

        ApiFuture<DocumentSnapshot> future1 = adminRef.get();
        DocumentSnapshot adminDoc = future1.get();
        String adminID = adminDoc.getString("adminID");

        ApiFuture<DocumentSnapshot> future1b = userRef.get();
        DocumentSnapshot userDoc = future1b.get();
        String username = userDoc.getString("username");

        ApiFuture<QuerySnapshot> future2 = db.collectionGroup("Messages")
                .whereEqualTo("adminID",adminID)
                .whereEqualTo("username",username).get();

        List<QueryDocumentSnapshot> documents = future2.get().getDocuments();

        for(QueryDocumentSnapshot document: documents) {
            list.add(new Messages(document.getId(), document.getDouble("messageID"), document.getString("username"),
                    document.getString("adminID"), document.getTimestamp("messDate"), document.getString("receive"),
                    document.getString("mContext")));
        }

        return list;
    }
}
