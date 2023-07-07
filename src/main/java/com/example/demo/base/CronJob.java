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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class CronJob {

    @Scheduled(cron = "0 */30 * * * *")
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

    @Scheduled(cron = "0 */30 * * * *")
    public void updateDonationTimes() {
        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference donorCollectionRef = firestore.collection("donor");
        CollectionReference donationHistoryCollectionRef = firestore.collection("donationHistory");

        // Get all donor documents
        ApiFuture<QuerySnapshot> donorQuerySnapshotFuture = donorCollectionRef.get();

        try {
            QuerySnapshot donorQuerySnapshot = donorQuerySnapshotFuture.get();
            List<QueryDocumentSnapshot> donorDocuments = donorQuerySnapshot.getDocuments();

            // Iterate through the donor documents
            for (QueryDocumentSnapshot donorDocument : donorDocuments) {
                String userId = donorDocument.getString("userId");

                // Retrieve donation history documents for the specific userId
                Query query = donationHistoryCollectionRef.whereEqualTo("donorId", userId);
                ApiFuture<QuerySnapshot> donationHistoryQuerySnapshotFuture = query.get();

                QuerySnapshot donationHistoryQuerySnapshot = donationHistoryQuerySnapshotFuture.get();
                long donationTimes = donationHistoryQuerySnapshot.size();

                // Update the donor's donationTimes field
                donorCollectionRef.document(donorDocument.getId())
                        .update("donationTimes", donationTimes);

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during the process
        }
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void updateExpiredAppointment() {
        Firestore firestore = FirestoreClient.getFirestore();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the current date to match the 'appmntDate' format in Firestore ('MMMM d, yyyy')
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String formattedCurrentDate = currentDate.format(formatter);

        // Create a query to filter appointments where 'appmntDate' is less than the current date and 'aStatus' is 'Pending'
        Query query = firestore.collection("appointment")
                .whereGreaterThan("appmntDate", formattedCurrentDate)
                .whereEqualTo("aStatus", "Pending");

        // Retrieve the appointments that match the query
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();

        try {
            QuerySnapshot querySnapshot = querySnapshotFuture.get();

            // Create a WriteBatch for batch updates
            WriteBatch batch = firestore.batch();

            for (QueryDocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                DocumentReference documentRef = documentSnapshot.getReference();

                // Update the 'aStatus' field to 'Expired' in the batch
                batch.update(documentRef, "aStatus", "Expired");
            }

            // Commit the batch updates
            batch.commit().get();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void updateExpiredAppointmentBySameMonth() {
        Firestore firestore = FirestoreClient.getFirestore();

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the current date to match the 'appmntDate' format in Firestore ('MMMM d, yyyy')
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String formattedCurrentDate = currentDate.format(formatter);

        // Create a query to filter appointments where 'appmntDate' is less than or equal to the current date
        Query query = firestore.collection("appointment")
                .whereLessThanOrEqualTo("appmntDate", formattedCurrentDate)
                .whereEqualTo("aStatus", "Pending");

        // Retrieve the appointments that match the query
        ApiFuture<QuerySnapshot> querySnapshotFuture = query.get();

        try {
            QuerySnapshot querySnapshot = querySnapshotFuture.get();

            // Create a WriteBatch for batch updates
            WriteBatch batch = firestore.batch();

            for (QueryDocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                DocumentReference documentRef = documentSnapshot.getReference();

                // Get the 'appmntDate' value from the document
                String appmntDate = documentSnapshot.getString("appmntDate");

                // Parse the 'appmntDate' string to LocalDate
                LocalDate appmntLocalDate = LocalDate.parse(appmntDate, formatter);

                // Compare the month and day of 'appmntDate' with the current date
                if (appmntLocalDate.getMonthValue() == currentDate.getMonthValue()
                        && appmntLocalDate.getDayOfMonth() < currentDate.getDayOfMonth()) {
                    // Update the 'aStatus' field to 'Expired' in the batch
                    batch.update(documentRef, "aStatus", "Expired");
                }
            }

            // Commit the batch updates
            batch.commit().get();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }
}
