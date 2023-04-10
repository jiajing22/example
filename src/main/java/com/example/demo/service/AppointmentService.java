package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AppointmentService extends GeneralService {
    private static final String COLLECTION_NAME = "appointment";

    public String addAppointment (Appointment appointment) throws ExecutionException, InterruptedException {
        appointment.setDocumentId(appointment.getDocumentId());
        return firestoreCreate(appointment, COLLECTION_NAME);
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
}
