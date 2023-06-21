package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.BDCentre;
import com.example.demo.entity.BloodGroup;
import com.example.demo.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BDCentreService extends GeneralService {
    private static final String COLLECTION_NAME = "bdcentre";
    private static final String COLLECTION_NAME_OTHER = "bloodGroup";

    public String addBDCentre(BDCentre bdCentre) throws ExecutionException, InterruptedException {
        String lastId = firestoreGetLastID(COLLECTION_NAME);
        bdCentre.setDocumentId(Util.generateId("BDCentre", lastId));
        bdCentre.setCentreId(bdCentre.getDocumentId());
        return firestoreCreate(bdCentre, COLLECTION_NAME);
    }

    public BDCentre getBDCentre(String documentId) throws ExecutionException, InterruptedException {
        return (BDCentre) firestoreGet(documentId, COLLECTION_NAME, BDCentre.class);
    }

    public String updateBDCentre(BDCentre bdCentre, String documentId) throws ExecutionException, InterruptedException {
        BDCentre isExist = getBDCentre(documentId);
        if (isExist != null) {
            bdCentre.setDocumentId(documentId);
            bdCentre.setCentreId(documentId);
            return firestoreUpdate(bdCentre, COLLECTION_NAME);
        }
        return null;
    }

    public String deleteBDCentre(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<BDCentre> getAllBDCentre() throws ExecutionException, InterruptedException {
        return firestoreGetAll(BDCentre.class, COLLECTION_NAME);
    }

    //    ---------------------------------Blood Group--------------------------------------------
    public BloodGroup getBloodGroup(String documentId) throws ExecutionException, InterruptedException {
        return (BloodGroup) firestoreGet(documentId, COLLECTION_NAME_OTHER, BloodGroup.class);
    }

    public String updateBloodGroup(BloodGroup bloodGroup, String documentId) throws ExecutionException, InterruptedException {
        bloodGroup.setDocumentId(documentId);
        bloodGroup.setNeeded(bloodGroup.getNeeded());
        return firestoreUpdate(bloodGroup, COLLECTION_NAME_OTHER);
    }
}
