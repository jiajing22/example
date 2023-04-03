package com.example.demo.base;

import com.example.demo.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class BaseCrudService {

    public String firestoreCreate(BaseEntity object, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(object.getDocumentId()).set(object);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Object firestoreGet (String id, String collection, Class<?> c) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(collection).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        System.out.println(c.getName());

        Object item;
        if (document.exists()) {
            item = document.toObject(c);
            return item;
        } else {
            System.out.println("Item not found");
            return null;
        }
    }

    public String firestoreUpdate(BaseEntity object, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(object.getDocumentId()).set(object);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String firestoreDelete(String documentId, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(documentId).delete();

        return "Document with Document ID " + documentId + " has been successfully deleted.";
    }

    public <T> List<T> firestoreGetAll (Class<T> c, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(collection).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<T> objectList = new ArrayList<>();
        T object = null;

        while(iterator.hasNext()){
            DocumentReference documentRef = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentRef.get();
            DocumentSnapshot document = future.get();

            object = document.toObject(c);
            objectList.add(object);
        }
        return objectList;
    }
}
