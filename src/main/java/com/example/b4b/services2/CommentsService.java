package com.example.restaurant.services2;

import com.example.restaurant.models2.Comments;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommentsService {

    public ArrayList<Comments> retrieveCommentsbyDrink(String id) throws ExecutionException, InterruptedException {
        ArrayList<Comments> list = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();


        DocumentReference drinkRef = db.collection("Drinks").document(id);
        ApiFuture<DocumentSnapshot> future1 = drinkRef.get();
        DocumentSnapshot drinkDoc = future1.get();
        Double drinkId = drinkDoc.getDouble("drinkID");

        ApiFuture<QuerySnapshot> future2 = db.collectionGroup("Comments").whereEqualTo("drinkID", drinkId).get();

        List<QueryDocumentSnapshot> documents = future2.get().getDocuments();

        for(QueryDocumentSnapshot document: documents) {
            Comments comment = new Comments();
            //retrieve document
            ApiFuture<DocumentSnapshot> drinkFuture = drinkRef.get();
            DocumentSnapshot drinkDoc2 = drinkFuture.get();

            if(drinkDoc2.exists())
                comment = drinkDoc2.toObject(Comments.class);

            list.add(new Comments(document.getId(), document.getDouble("commentID"), document.getDouble("drinkID"), document.getString("username"), document.getString("cContext")));
        }

        return list;
    }

    /*
    public void newComment (Comments comment) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        //Comments commentx = new Comments();

        ApiFuture<DocumentReference> commentRef = db.collection("Comments").add(comment);
    }
     */
}
