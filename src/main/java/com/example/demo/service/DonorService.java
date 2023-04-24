package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Donor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DonorService extends GeneralService {
    private static final String COLLECTION_NAME = "users";

    public String addDonor (Donor donor) throws ExecutionException, InterruptedException {
        donor.setDocumentId(donor.getDocumentId());
        System.out.println("Inside");
        System.out.println(donor);
        return firestoreCreate(donor, COLLECTION_NAME);
    }

    //Get By id
    public Donor getDonor(String donorId) throws ExecutionException, InterruptedException {
        return (Donor)firestoreGet(donorId, COLLECTION_NAME, Donor.class);
    }

    public String updateDonor (Donor donor) throws ExecutionException, InterruptedException {
        donor.setDocumentId(donor.getDocumentId());
        return firestoreUpdate(donor, COLLECTION_NAME);
    }

    public String deleteDonor(String donorId) throws ExecutionException, InterruptedException {
        return firestoreDelete(donorId, COLLECTION_NAME);
    }

    public List<Donor> getAllDonor() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Donor.class, COLLECTION_NAME, "D");
    }
}
