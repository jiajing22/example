package com.example.demo.base;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GeneralService {

    public String firestoreCreate(GeneralEntity object, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFirestore.collection(collection).document(object.getDocumentId());
        ApiFuture<DocumentSnapshot> docSnapshotFuture = docRef.get();
        DocumentSnapshot docSnapshot = docSnapshotFuture.get();
        if (docSnapshot.exists()) {
            return "Document with ID " + object.getDocumentId() + " already exists in collection " + collection;
        }

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
            return null;
        }
    }

    public String firestoreGetIdByUsername (String username, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = dbFirestore.collection(collection);
        Query query = collectionRef.whereEqualTo("userId", username).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        if (documents.size() > 0) {
            return documents.get(0).getId();
        } else {
            return null;
        }
    }

    public String firestoreGetIdByCredentials(String username, String password, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(collection);
        Query query = collectionReference.whereEqualTo("userId", username).whereEqualTo("password", password);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            return document.getId();
        }
        return null;
    }

    public String firestoreUpdate(GeneralEntity object, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(object.getDocumentId()).set(object);

        //Need to find the document ID first
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

    public <T> List<T> firestoreGetAll (Class<T> c, String collection, String prefix) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(collection).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        Query query = dbFirestore.collection(collection).whereGreaterThanOrEqualTo(FieldPath.documentId(), prefix)
                .whereLessThan(FieldPath.documentId(), prefix + Character.MAX_VALUE);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<T> objectList = new ArrayList<>();
        T object = null;

        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            object = document.toObject(c);
            objectList.add(object);
        }

//        while(iterator.hasNext()){
//            DocumentReference documentRef = iterator.next();
//            ApiFuture<DocumentSnapshot> future = documentRef.get();
//            DocumentSnapshot document = future.get();
//
//            object = document.toObject(c);
//            objectList.add(object);
//        }
        return objectList;
    }
}
