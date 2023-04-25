package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Donor;
import com.example.demo.entity.Staff;
import com.example.demo.entity.User;
import com.example.demo.util.SHA256;
import com.google.cloud.Timestamp;
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
    public Donor getDonor(String donorId, String prefix) throws ExecutionException, InterruptedException {
        return (Donor)firestoreGet(donorId, COLLECTION_NAME, Donor.class, prefix);
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

    public Donor getById(String userId) throws ExecutionException, InterruptedException {
        return (Donor)firestoreGet(userId, COLLECTION_NAME, Donor.class);
    }

    public String getUserIdByUsername(String username) throws ExecutionException, InterruptedException {
        return firestoreGetIdByUsername(username, COLLECTION_NAME);
    }

    public String getUserIdByCredentials(String username, String password) throws ExecutionException, InterruptedException {
        return firestoreGetIdByCredentials(username, password, COLLECTION_NAME);
    }

    public Donor validateDonorLogin(String userUserName, String userPw) throws Exception {
        String id = null;
        id = getUserIdByUsername(userUserName);

        if (id != null && !id.isEmpty()) {

            String hashPassword = SHA256.hash(userPw);
            System.out.println("Hashed" + hashPassword);
            id = getUserIdByCredentials(userUserName, hashPassword);

            if (id != null && !id.isEmpty()) {
                Donor donorWithIdOnly = new Donor();
                donorWithIdOnly.setUserId(id);
                Donor updateDonorLogin = (Donor)firestoreGet(id, COLLECTION_NAME, Donor.class);
                updateDonorLogin.setUserLastLoginDate(Timestamp.now());
                String updateLoginD = firestoreUpdate(updateDonorLogin, COLLECTION_NAME);

                return donorWithIdOnly;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
