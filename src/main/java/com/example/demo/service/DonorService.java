package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Donor;
import com.example.demo.entity.PasswordResetToken;
import com.example.demo.util.SHA256;
import com.example.demo.util.Util;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class DonorService extends GeneralService {
    private static final String COLLECTION_NAME = "donor";
    private static final String USER_TYPE = "donor";

    public String addDonor (Donor donor) throws Exception {
        int id= getAllDonor().size()+1;
        donor.setDocumentId(Util.generateId("Donor",id));
        donor.setDonorId(Util.generateId("Donor",id));
        donor.setPassword(SHA256.hash(donor.getPassword()));
        donor.setUserType(USER_TYPE);
        return firestoreAddNewUser(donor, COLLECTION_NAME, donor.getUserId());
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
        return firestoreGetAll(Donor.class, COLLECTION_NAME);
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

    public Boolean isUserExist(String username, String email) throws ExecutionException, InterruptedException {
        return firestoreCheckUserEmail(username, email, COLLECTION_NAME);
    }

    public Donor findDonorById(String userId) throws ExecutionException, InterruptedException {
        Donor donorInfo = (Donor)firestoreGetByUserId(userId, COLLECTION_NAME, Donor.class);
        if(donorInfo == null){
            return null;
        }
        Donor donorNeededInfo = new Donor();
        donorNeededInfo.setUserId(donorInfo.getUserId());
        donorNeededInfo.setFullName(donorInfo.getFullName());
        donorNeededInfo.setBloodType(donorInfo.getBloodType());
        System.out.println(donorNeededInfo);
        return donorNeededInfo;
    }

    public String forgetPw (String username, String email) throws ExecutionException, InterruptedException {
        boolean check = isUserExist(username,email);
        System.out.println("Check: "+ isUserExist(username,email));
        if(check){
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setDocumentId(token);
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setCreated(Timestamp.now());
            String status = firestoreCreate(resetToken,"passwordResetToken");
            System.out.println(status);
            return token;
        } else {
            return null;
        }
    }

    public String matchToken (String token) throws ExecutionException, InterruptedException {
        PasswordResetToken checkToken = new PasswordResetToken();
        checkToken =  (PasswordResetToken) firestoreGet(token, "passwordResetToken", PasswordResetToken.class);
        System.out.println(checkToken);
        return checkToken.getEmail();
    }

//    public List<Donor> findDonorById(String userId) throws ExecutionException, InterruptedException {
//        List<Donor> donors = firestoreGetByUserId(userId, COLLECTION_NAME, Donor.class, "D");
//        if (donors == null || donors.isEmpty()) {
//            return null;
//        }
//
//        List<Donor> result = new ArrayList<>();
//        for (Donor donorInfo : donors) {
//            Donor donorNeededInfo = new Donor();
//            donorNeededInfo.setUserId(donorInfo.getUserId());
//            donorNeededInfo.setFullName(donorInfo.getFullName());
//            donorNeededInfo.setBloodType(donorInfo.getBloodType());
//            result.add(donorNeededInfo);
//        }
//        return result;
//    }


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
