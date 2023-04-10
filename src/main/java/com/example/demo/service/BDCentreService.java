package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.BDCentre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BDCentreService extends GeneralService {
    private static final String COLLECTION_NAME = "bdcentre";

    public String addBDCentre (BDCentre bdCentre) throws ExecutionException, InterruptedException {
        bdCentre.setDocumentId(bdCentre.getDocumentId());
        return firestoreCreate(bdCentre, COLLECTION_NAME);
    }

    public BDCentre getBDCentre(String documentId) throws ExecutionException, InterruptedException {
        return (BDCentre)firestoreGet(documentId, COLLECTION_NAME, BDCentre.class);
    }

    public String updateBDCentre (BDCentre bdCentre) throws ExecutionException, InterruptedException {
        bdCentre.setDocumentId(bdCentre.getDocumentId());
        return firestoreUpdate(bdCentre, COLLECTION_NAME);
    }

    public String deleteBDCentre(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<BDCentre> getAllBDCentre() throws ExecutionException, InterruptedException {
        return firestoreGetAll(BDCentre.class, COLLECTION_NAME);
    }
}
