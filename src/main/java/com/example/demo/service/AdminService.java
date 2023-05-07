package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Staff;
import com.example.demo.util.SHA256;
import com.google.cloud.Timestamp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AdminService extends GeneralService {
    private static final String COLLECTION_NAME = "admin";

    public String addAdmin (Admin admin) throws ExecutionException, InterruptedException {
        admin.setDocumentId(admin.getDocumentId());
        return firestoreCreate(admin, COLLECTION_NAME);
    }

    public Admin getAdmin(String adminId) throws ExecutionException, InterruptedException {
        return (Admin)firestoreGet(adminId, COLLECTION_NAME, Admin.class);
    }

    public String updateAdmin (Admin admin) throws ExecutionException, InterruptedException {
        admin.setDocumentId(admin.getDocumentId());
        return firestoreUpdate(admin, COLLECTION_NAME);
    }

    public String deleteAdmin(String adminId) throws ExecutionException, InterruptedException {
        return firestoreDelete(adminId, COLLECTION_NAME);
    }

    public List<Admin> getAllAdmin() throws ExecutionException, InterruptedException {
        return firestoreGetAll(Admin.class, COLLECTION_NAME, "A");
    }

    public String getUserIdByCredentials(String username, String password) throws ExecutionException, InterruptedException {
        return firestoreGetIdByCredentials(username, password, COLLECTION_NAME);
    }

//    public Admin validateAdminLogin(String userName, String password) throws Exception {
//        String id = getUserIdByCredentials(userName, SHA256.hash(password));
//
//        if (id != null && !id.isEmpty()) {
//            Staff staffWithIdOnly = new Staff();
//            staffWithIdOnly.setUserId(id);
//            Staff updateStaffLogin = (Staff)firestoreGet(id, COLLECTION_NAME, Staff.class);
//            updateStaffLogin.setUserLastLoginDate(Timestamp.now());
//            String updateLoginS = firestoreUpdate(updateStaffLogin, COLLECTION_NAME);
//            return staffWithIdOnly;
//        } else {
//            return null;
//        }
//
//    }

    public Admin validateAdminLogin(String userName, String password) throws Exception {
        System.out.println(SHA256.hash(password));
        String id = getUserIdByCredentials(userName, SHA256.hash(password));
        System.out.println(id);

        if (id != null && !id.isEmpty()) {
            Admin adminWithIdOnly = new Admin();
            adminWithIdOnly.setUserId(id);
            Admin updateAdminLogin = (Admin)firestoreGet(id, COLLECTION_NAME, Admin.class);
            updateAdminLogin.setUserLastLoginDate(Timestamp.now());
            String updateLoginS = firestoreUpdate(updateAdminLogin, COLLECTION_NAME);
            return adminWithIdOnly;
        } else {
            return null;
        }

    }
}
