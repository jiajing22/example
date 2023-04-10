package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.RegistrationForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RegistrationFormService extends GeneralService {
    private static final String COLLECTION_NAME = "registrationForm";

    public String addRegForm (RegistrationForm regForm) throws ExecutionException, InterruptedException {
        regForm.setDocumentId(regForm.getDocumentId());
        return firestoreCreate(regForm, COLLECTION_NAME);
    }

    public RegistrationForm getRegForm(String documentId) throws ExecutionException, InterruptedException {
        return (RegistrationForm)firestoreGet(documentId, COLLECTION_NAME, RegistrationForm.class);
    }

    public String updateRegForm (RegistrationForm regForm) throws ExecutionException, InterruptedException {
        regForm.setDocumentId(regForm.getDocumentId());
        return firestoreUpdate(regForm, COLLECTION_NAME);
    }

    public String deleteRegForm(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<RegistrationForm> getAllRegForm() throws ExecutionException, InterruptedException {
        return firestoreGetAll(RegistrationForm.class, COLLECTION_NAME);
    }
}
