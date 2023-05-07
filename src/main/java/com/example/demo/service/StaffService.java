package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Staff;
import com.example.demo.util.SHA256;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StaffService extends GeneralService {

    private static final String COLLECTION_NAME = "staff";
    private static final String USER_TYPE = "staff";

    public String addStaff (Staff staff) throws ExecutionException, InterruptedException {
//        int id= getAllStaff().size()+1;
        staff.setDocumentId(staff.getDocumentId());
        staff.setUserType(USER_TYPE);
//        staff.setUserType(USER_TYPE);
//        staff.setDate(Timestamp.now());
//        System.out.println("AA");
//        System.out.println(Timestamp.now().toDate());
        return firestoreCreate(staff, COLLECTION_NAME);
    }

    public Staff getStaff(String documentId) throws ExecutionException, InterruptedException {
        return (Staff)firestoreGet(documentId, COLLECTION_NAME, Staff.class);
    }

    public String updateStaff (Staff staff) throws ExecutionException, InterruptedException {
        staff.setDocumentId(staff.getDocumentId());
        return firestoreUpdate(staff, COLLECTION_NAME);
    }

    public String deleteStaff(String documentId) throws ExecutionException, InterruptedException {
        return firestoreDelete(documentId, COLLECTION_NAME);
    }

    public List<Staff> getAllStaff() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Staff.class, COLLECTION_NAME, "ST");
    }

    public String getUserIdByUsername(String username) throws ExecutionException, InterruptedException {
        return firestoreGetIdByUsername(username, COLLECTION_NAME);
    }

    public Staff getById(String userId) throws ExecutionException, InterruptedException {
        return (Staff)firestoreGet(userId, COLLECTION_NAME, Staff.class);
    }

    public String getUserIdByCredentials(String username, String password) throws ExecutionException, InterruptedException {
        return firestoreGetIdByCredentials(username, password, COLLECTION_NAME);
    }

//    public String validateStaffLogin(String userUserName, String userPw) {
//        String id = null;
//
//        try {
//            id = getUserIdByUsername(userUserName);
//
//            if (id != null && !id.isEmpty()) {
//                Staff staff = getById(id);
//                String hashPassword = SHA256.hash(userPw);
//                System.out.println("Hashed" + hashPassword);
//                id = getUserIdByCredentials(userUserName, hashPassword);
//
//                if (id != null && !id.isEmpty()) {
//                    Staff updateStaffLogin = (Staff) firestoreGet(id, COLLECTION_NAME, Staff.class);
//                    updateStaffLogin.setUserLastLoginDate(Timestamp.now());
//                    String updateLoginS = firestoreUpdate(updateStaffLogin, COLLECTION_NAME);
//                    return id;
//                } else {
//                    return "Incorrect username or password.";
//                }
//            } else {
//                return "Incorrect username or password.";
//            }
//        } catch (Exception e) {
//            return "An error occurred while validating user login: " + e.getMessage();
//        }
//    }

    public Staff validateStaffLogin(String userName, String password) throws Exception {
        System.out.println(SHA256.hash(password));
        String id = getUserIdByCredentials(userName, SHA256.hash(password));

        if (id != null && !id.isEmpty()) {
            Staff staffWithIdOnly = new Staff();
            staffWithIdOnly.setUserId(id);
            Staff updateStaffLogin = (Staff)firestoreGet(id, COLLECTION_NAME, Staff.class);
            updateStaffLogin.setUserLastLoginDate(Timestamp.now());
            String updateLoginS = firestoreUpdate(updateStaffLogin, COLLECTION_NAME);
            return staffWithIdOnly;
        } else {
            return null;
        }

    }
}