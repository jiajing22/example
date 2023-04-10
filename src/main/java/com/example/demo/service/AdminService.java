package com.example.demo.service;

import com.example.demo.base.GeneralService;
import com.example.demo.entity.Admin;
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
}
