package com.example.demo.base;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.cloud.firestore.CollectionReference;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class CronJob {

    @Scheduled(cron = "0 */30 * * * *") // Run daily at midnight
    public void updateExpiredDocuments() {
        Firestore firestore = FirestoreClient.getFirestore();

        // Calculate the expiration timestamp (1 day ago)
        Instant expirationInstant = Instant.now().minus(Duration.ofDays(1));
        Timestamp expirationTimestamp = Timestamp.ofTimeSecondsAndNanos(expirationInstant.getEpochSecond(), expirationInstant.getNano());

        // Get the Firestore collection reference
        CollectionReference collectionRef = firestore.collection("registrationForm");

        // Create a query to filter documents where 'submitTime' is less than the expiration timestamp
        Query query = collectionRef
                .whereLessThan("submitTime", expirationTimestamp)
                .whereEqualTo("formStatus", "submitted");

        // Retrieve the documents that match the query
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();

        try {
            QuerySnapshot querySnapshot = querySnapshotFuture.get();

            // Create a WriteBatch for batch updates
            WriteBatch batch = firestore.batch();

            for (QueryDocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                DocumentReference documentRef = documentSnapshot.getReference();

                // Update the 'formStatus' field to 'expired' in the batch
                batch.update(documentRef, "formStatus", "expired");
            }

            // Commit the batch updates
            batch.commit().get();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }
}
