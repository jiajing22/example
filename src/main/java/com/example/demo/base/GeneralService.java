package com.example.demo.base;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.DonationHistory;
import com.example.demo.entity.Donor;
import com.example.demo.entity.PasswordResetToken;
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
            return null;
        }

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(object.getDocumentId()).set(object);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Object firestoreGet(String id, String collection, Class<?> c) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(collection).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Object item;
        if (document.exists()) {
            item = document.toObject(c);
            return item;
        } else {
            return null;
        }
    }

    public Object firestoreGet(String id, String collection, Class<?> c, String prefix) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = dbFirestore.collection(collection);
        Query query = collectionRef.whereGreaterThanOrEqualTo(FieldPath.documentId(), prefix)
                .whereLessThan(FieldPath.documentId(), prefix + Character.MAX_VALUE)
                .whereEqualTo("documentId", id).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if (documents.size() > 0) {
            DocumentSnapshot document = documents.get(0);
            Object item = document.toObject(c);
            return item;
        } else {
            return null;
        }
    }

    public Object firestoreGetByUserId(String id, String collection, Class<?> c) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = dbFirestore.collection(collection);
        Query query = collectionRef.whereEqualTo("userId", id).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if (documents.size() > 0) {
            DocumentSnapshot document = documents.get(0);
            Object item = document.toObject(c);
            return item;
        } else {
            return null;
        }
    }

    public String firestoreGetIdByUsername(String username, String collection) throws ExecutionException, InterruptedException {
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
        DocumentReference documentRef = dbFirestore.collection(collection).document(object.getDocumentId());
        ApiFuture<DocumentSnapshot> documentSnapshotFuture = documentRef.get();

        DocumentSnapshot documentSnapshot = documentSnapshotFuture.get();
        if (documentSnapshot.exists()) {
            // Document exists, perform the update
            ApiFuture<WriteResult> collectionApiFuture = documentRef.set(object);
            return "success";
        } else {
            // Document does not exist
            return null;
        }
    }

    public String firestoreDelete(String documentId, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(documentId).delete();

        return "Document with Document ID " + documentId + " has been successfully deleted.";
    }

    public <T> List<T> firestoreGetAll(Class<T> c, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReference = dbFirestore.collection(collection).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<T> objectList = new ArrayList<>();
        T object = null;

        while (iterator.hasNext()) {
            DocumentReference documentRef = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentRef.get();
            DocumentSnapshot document = future.get();

            object = document.toObject(c);
            objectList.add(object);
        }
        return objectList;
    }

    public <T> List<T> firestoreGetAll(Class<T> c, String collection, String prefix) throws ExecutionException, InterruptedException {
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
        return objectList;
    }

    public String firestoreGetLastID(String collection) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(collection);

        Query query = collectionRef.orderBy("documentId", Query.Direction.DESCENDING).limit(1);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot snapshot = future.get();

        if (!snapshot.isEmpty()) {
            DocumentSnapshot lastDocument = snapshot.getDocuments().get(0);
            return lastDocument.getId();
        }

        return null; // Collection is empty
    }


    public <T> List<T> firestoreGetByIc(Class<T> c, String collection, String ic) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(collection);
        Query query = collectionRef.whereEqualTo("donorId", ic);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<T> resultList = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            T obj = document.toObject(c);
            resultList.add(obj);
        }
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList;
    }

//    -------------------------------------------------------------------------------------------------------------------------------------------------

    public Boolean firestoreCheckUserEmail(String username, String email, String collection) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        Query query = firestore.collection(collection)
                .whereEqualTo("userId", username)
                .whereEqualTo("email", email)
                .limit(1);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot snapshot = future.get();

        if (snapshot.isEmpty()) {
            // No matching documents found
            return false;
        } else {
            // At least one matching document found
            DocumentReference docRef = snapshot.getDocuments().get(0).getReference();
            String userId = docRef.getId();
            return true;
        }
    }

    public Donor firestoreGetByEmail(String email, String collection) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        Query query = firestore.collection(collection)
                .whereEqualTo("email", email)
                .limit(1);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot snapshot = future.get();

        if (snapshot.isEmpty()) {
            // No matching documents found
            return null;
        } else {
            // At least one matching document found
            DocumentReference docRef = snapshot.getDocuments().get(0).getReference();
            String id = docRef.getId();
            Donor donor = snapshot.getDocuments().get(0).toObject(Donor.class);
            donor.setDocumentId(id);
            return donor;
        }
    }

    public String firestoreAddNewUser(Donor donor, String collection, String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference docRef = dbFirestore.collection(collection).document(donor.getDocumentId());
        ApiFuture<DocumentSnapshot> docSnapshotFuture = docRef.get();
        DocumentSnapshot docSnapshot = docSnapshotFuture.get();
        if (docSnapshot.exists()) {
            return "documentId";
        }

        Query query = dbFirestore.collection(collection).whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotFuture.get();
        if (!querySnapshot.isEmpty()) {
            return "userId";
        }

        Query query2 = dbFirestore.collection(collection).whereEqualTo("email", donor.getEmail());
        ApiFuture<QuerySnapshot> querySnapshotFuture2 = query2.get();
        QuerySnapshot querySnapshot2 = querySnapshotFuture2.get();
        if (!querySnapshot2.isEmpty()) {
            return "email";
        }

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(donor.getDocumentId()).set(donor);
        return "success";
    }

    public boolean findToken(String collection, String token) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(collection);

        try {
            Query query = collectionRef.whereEqualTo("verifiedToken", token)
                    .whereEqualTo("isVerified", false)
                    .limit(1);
            QuerySnapshot querySnapshot = query.get().get();
            if (!querySnapshot.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // No matching token found
    }

    public PasswordResetToken findPasswordToken(String collection, String token) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(collection);

        try {
            Query query = collectionRef.whereEqualTo("token", token)
                    .whereEqualTo("isExpired", false)
                    .limit(1);
            QuerySnapshot querySnapshot = query.get().get();
            if (!querySnapshot.isEmpty()) {
                return querySnapshot.getDocuments().get(0).toObject(PasswordResetToken.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // No matching token found
    }

    public Donor getDonorInfoByToken(String collection, String token) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(collection);
        try {
            // Create a query to search for documents with a matching token
            Query query = collectionRef.whereEqualTo("verifiedToken", token).limit(1);

            // Execute the query and get the matching documents
            ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();
            QuerySnapshot querySnapshot = querySnapshotFuture.get();

            // If there is a matching document, extract the donor information
            if (!querySnapshot.isEmpty()) {
                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                return documentSnapshot.toObject(Donor.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null; // No matching document found or an exception occurred
    }

    public String firestoreCreateAppmt(Appointment appointment, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Check if a document with the same donorId and appmntDate already exists
        Query query = dbFirestore.collection(collection)
                .whereEqualTo("donorId", appointment.getDonorId())
                .whereEqualTo("appmntDate", appointment.getAppmntDate());

        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();
        QuerySnapshot querySnapshot = querySnapshotFuture.get();

        if (!querySnapshot.isEmpty()) {
            return "exist";
        }

        DocumentReference docRef = dbFirestore.collection(collection).document(appointment.getDocumentId());
        ApiFuture<DocumentSnapshot> docSnapshotFuture = docRef.get();
        DocumentSnapshot docSnapshot = docSnapshotFuture.get();

        if (docSnapshot.exists()) {
            return null;
        }

        ApiFuture<WriteResult> collectionApiFuture = docRef.set(appointment);
        return "success";
    }
//    ----------------------------------------------------------------------------------------------------

    public DonationHistory getHistoryById(String recId, String collection) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        Query query = firestore.collection(collection)
                .whereEqualTo("recordId", recId)
                .limit(1);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot snapshot = future.get();

        if (snapshot.isEmpty()) {
            // No matching documents found
            return null;
        } else {
            // At least one matching document found
            DocumentReference docRef = snapshot.getDocuments().get(0).getReference();
            String id = docRef.getId();
            DonationHistory donationHistory = snapshot.getDocuments().get(0).toObject(DonationHistory.class);
            donationHistory.setDocumentId(id);
            return donationHistory;
        }
    }

    //    ---------------------------------------------Update Password Only-----------------------------------------
    public String firestoreUpdatePassword(String userId, String newPassword, String collection) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = dbFirestore.collection(collection);

        Query query = collectionRef.whereEqualTo("userId", userId);
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();

        QuerySnapshot querySnapshot = querySnapshotFuture.get();
        if (!querySnapshot.isEmpty()) {
            // Retrieve the first matching document
            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);

            // Update only the password field
            documentSnapshot.getReference().update("password", newPassword);

            return "success";
        } else {
            // Document with matching userId does not exist
            return null;
        }
    }
}
