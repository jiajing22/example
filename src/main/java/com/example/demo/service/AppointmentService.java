package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.DonationHistory;
import com.example.demo.util.Util;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AppointmentService extends GeneralService {
    private static final String COLLECTION_NAME = "appointment";

    public String addAppointment (Appointment appointment) throws ExecutionException, InterruptedException {
        String lastID = firestoreGetLastID(COLLECTION_NAME);
        appointment.setDocumentId(Util.generateId("Appointment",lastID));
        appointment.setAppointmentId(appointment.getDocumentId());
        return firestoreCreateAppmt(appointment, COLLECTION_NAME);
    }

    public Appointment getAppointment(String documentId) throws ExecutionException, InterruptedException {
        return (Appointment)firestoreGet(documentId, COLLECTION_NAME, Appointment.class);
    }

    public String updateAppointment (Appointment appointment) throws ExecutionException, InterruptedException {
        appointment.setDocumentId(appointment.getDocumentId());
        return firestoreUpdate(appointment, COLLECTION_NAME);
    }

    public String deleteAppointment(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<Appointment> getAllAppointment() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Appointment.class, COLLECTION_NAME);
    }

    public List <Appointment> getAppointmentListByIc (String ic) throws ExecutionException, InterruptedException{
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(COLLECTION_NAME);
        Query query = collectionRef.whereEqualTo("donorId", ic);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<Appointment> appointments = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Appointment appointment = document.toObject(Appointment.class);
            appointments.add(appointment);
        }
        if (appointments.isEmpty()){
            return null;
        }
        return appointments;
    }
}
